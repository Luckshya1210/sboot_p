package com.example.demo.services;

import com.example.demo.entity.JEntry;
import com.example.demo.entity.User;
import com.example.demo.repository.JournalEntryRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Slf4j
@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;
    @Autowired
    private UserEntryService userService;
    @Transactional
    public void saveEntry(JEntry je, String userName){
        try{

            User user=userService.findByUsername(userName);
            JEntry saved=journalEntryRepo.save(je);
            user.getJournalEntries().add(saved);
            userService.saveJEntry(user);

        }catch(Exception e){
            throw new RuntimeException("Some exception occured",e);
        }
    }
    public void saveEntryUpd(JEntry je){
        journalEntryRepo.save(je);
    }
    public List<JEntry> getAll(){
        return journalEntryRepo.findAll();
    }

    public Optional<JEntry> findById(ObjectId id){
        return journalEntryRepo.findById(id);
    }
    private static final Logger logger= LoggerFactory.getLogger(JournalEntryService.class);
    @Transactional
    public boolean deletebyid(ObjectId id, String userName){
        boolean removed=false;
        try{
            User user=userService.findByUsername(userName);
            removed= user.getJournalEntries().removeIf(x->x.getId().equals(id));
            if(removed){
                userService.saveJEntry(user);
                journalEntryRepo.deleteById(id);
            }

        }catch (Exception e){
//            System.out.println(e);
            log.error("some exception occured "+e);
            throw new RuntimeException("Some exception occured",e);
        }

        return removed;

    }

//    public List<JEntry> findByUserName(String userName){
//
//    }

}

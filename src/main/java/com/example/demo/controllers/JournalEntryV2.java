package com.example.demo.controllers;

import com.example.demo.entity.JEntry;
import com.example.demo.entity.User;
import com.example.demo.services.JournalEntryService;
import com.example.demo.services.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryV2 {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserEntryService userService;


    @GetMapping
    public ResponseEntity<?> getAllEntriesByusername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User user=userService.findByUsername(userName);
        List<JEntry> journal= user.getJournalEntries();
        if(journal!=null && !journal.isEmpty()){
            return new ResponseEntity<>(journal,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JEntry> createEntry(@RequestBody JEntry myentry){

        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName=authentication.getName();
            myentry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myentry,userName);
            return new ResponseEntity<>(myentry,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JEntry> getByid(@PathVariable ObjectId myId){


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User user = userService.findByUsername(userName);
        List<JEntry> collect=user.getJournalEntries().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<JEntry> js= journalEntryService.findById(myId);
            if(js.isPresent()){
                return new ResponseEntity<>(js.get(), HttpStatus.OK);
            }

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("id/{myid}")
    public ResponseEntity<?> delByid(@PathVariable ObjectId myid){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        boolean removed=journalEntryService.deletebyid(myid,userName);
        if(removed){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("id/{myid}")
    public ResponseEntity<?> updateByid(@PathVariable ObjectId myid,@RequestBody JEntry entry ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User user = userService.findByUsername(userName);
        List<JEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myid)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            JEntry old=journalEntryService.findById(myid).orElse(null);
            if(old!=null){
                old.setTitle(entry.getTitle()!=null && !entry.getTitle().equals("") ? entry.getTitle() : old.getTitle() );
                old.setContent(entry.getContent()!=null && !entry.getContent().equals("") ? entry.getContent() : old.getContent() );
                journalEntryService.saveEntryUpd(old);
                return new ResponseEntity<>(old,HttpStatus.OK);
            }
        }


        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }

    
}

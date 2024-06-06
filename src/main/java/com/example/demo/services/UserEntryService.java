package com.example.demo.services;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserEntryService {

    @Autowired
    private UserRepo userRepo;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveEntry(User je){
        je.setpassword(passwordEncoder.encode(je.getpassword()));
        je.setRoles(Arrays.asList("USER"));
        userRepo.save(je);

    }

    public void saveJEntry(User user){
        userRepo.save(user);
    }

    public void saveAdmin(User user){
        user.setRoles(Arrays.asList("USER","ADMIN"));
        user.setpassword(passwordEncoder.encode(user.getpassword()));
        userRepo.save(user);
    }

    public List<User> getAll(){
        return userRepo.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepo.findById(id);
    }

    public void deletebyid(ObjectId id){
        userRepo.deleteById(id);
    }

    public User findByUsername(String un){
        return userRepo.findByUserName(un);
    }

}

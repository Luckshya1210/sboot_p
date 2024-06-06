package com.example.demo.controllers;

import com.example.demo.entity.User;
import com.example.demo.services.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserEntryService userEntryService;

    @GetMapping("all-users")
    public ResponseEntity<?> getAllusers(){
        List<User> users=userEntryService.getAll();
        if(users!=null && !users.isEmpty() ){
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return null;
    }

    @PostMapping("create-admin")
    public void createAdmin(@RequestBody User user){
        userEntryService.saveAdmin(user);
    }
}

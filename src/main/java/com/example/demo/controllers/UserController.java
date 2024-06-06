package com.example.demo.controllers;

import com.example.demo.apiResponse.WeatherResponse;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;
import com.example.demo.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.example.demo.services.UserEntryService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserEntryService userService;
    @Autowired
    private WeatherService weatherService;


    @PutMapping
    public ResponseEntity<?> updatedUser(@RequestBody User usr){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User userdb=userService.findByUsername(userName);
        if(userdb!=null){
            userdb.setuserName(usr.getuserName());
            userdb.setpassword(usr.getpassword());
            userService.saveEntry(userdb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        userRepo.deleteByUserName(userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greetings(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        String greetings="";
        WeatherResponse weatherResponse=weatherService.getWeater("mumbai");
        if(weatherResponse!=null){
            greetings=" Weather feels like "+ weatherResponse.getcurrentConditions().getFeelslike();
        }
        return new ResponseEntity<>("Hi "+userName+greetings,HttpStatus.OK);
    }
    
}

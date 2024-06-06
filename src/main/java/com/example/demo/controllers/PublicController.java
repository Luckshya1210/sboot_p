package com.example.demo.controllers;

import com.example.demo.entity.User;
import com.example.demo.services.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserEntryService userService;

    @GetMapping("health-check")
    public String getHealth(){
        return "OK";
    }

    @PostMapping("create-user")
    public void createUser(@RequestBody User user){
        userService.saveEntry(user);
    }
}

//package com.example.demo.services;
//
//import com.example.demo.repository.UserRepo;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentMatchers;
//import org.mockito.Mock;
//import org.mockito.Mockito.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//public class UserDetailServiceImpTest {
//
//    @Autowired
//    private UserDetailsServiceImp userDetailsService;
//
//    @Mock
//    private UserRepo userRepo;
//
//    @Test
//    public void loadbyusernametest(){
////        when(userRepo.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("ram").roles(new ArrayList<>()).password("ram")).build();
//        UserDetails user=userDetailsService.loadUserByUsername("ram");
//        Assertions.assertNotNull(user);
//    }
//
//
//}

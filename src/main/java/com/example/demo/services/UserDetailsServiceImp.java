package com.example.demo.services;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(username);
        if(user!=null){
            UserDetails userDetails=org.springframework.security.core.userdetails.User.builder()
                    .username(user.getuserName())
                    .password(user.getpassword())
                    .roles(user.getRoles().toArray(new String[0]))
                    .build();
            return userDetails;
        }
        return null;
    }


}

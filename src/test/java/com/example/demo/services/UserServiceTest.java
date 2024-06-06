package com.example.demo.services;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserRepo userRepo;


    @ParameterizedTest
    //valuesource or enumsource/csvsource
    @ValueSource(strings={
            "a",
            "ram",
            "g"
    })
    public void addtest(String name) {
//        User user=userRepo.findByUserName(name);
//        assertNotNull(userRepo.findByUserName("a"));
        assertNotNull(userRepo.findByUserName(name),"failed for "+ name);
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,2,4",
            "2,4,2"
    })
    public void test(int a,int b,int exp){
        assertEquals(exp,a+b);
    }


}

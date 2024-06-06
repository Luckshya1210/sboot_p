package com.example.demo.repository;

import com.example.demo.entity.JEntry;
import com.example.demo.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, ObjectId> {
    User findByUserName(String userName);
    void deleteByUserName(String userName);
}

package com.example.demo.repository;

import com.example.demo.entity.JEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepo extends MongoRepository<JEntry, ObjectId> {

}

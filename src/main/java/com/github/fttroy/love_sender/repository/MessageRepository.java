package com.github.fttroy.love_sender.repository;

import com.github.fttroy.love_sender.document.Message;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MessageRepository extends MongoRepository<Message, ObjectId> {
    Optional<Message> findByEmailAdress(String emailAdress);
}

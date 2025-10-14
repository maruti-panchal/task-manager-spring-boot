package com.learnspring.taskmanager.repository;

import com.learnspring.taskmanager.model.User;
import lombok.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    User findUserByUsername(String username);
}

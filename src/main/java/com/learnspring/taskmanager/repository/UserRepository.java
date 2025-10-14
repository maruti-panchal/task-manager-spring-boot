package com.learnspring.taskmanager.repository;

import com.learnspring.taskmanager.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}

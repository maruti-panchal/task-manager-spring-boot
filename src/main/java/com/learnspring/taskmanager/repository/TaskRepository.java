package com.learnspring.taskmanager.repository;

import com.learnspring.taskmanager.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {
}

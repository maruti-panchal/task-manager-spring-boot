package com.learnspring.taskmanager.repository;

import com.learnspring.taskmanager.dtos.TaskRequestDto;
import com.learnspring.taskmanager.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {

    @Override
    <S extends Task> S save(S entity);
}

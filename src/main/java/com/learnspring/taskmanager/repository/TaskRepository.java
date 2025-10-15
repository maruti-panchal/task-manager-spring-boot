package com.learnspring.taskmanager.repository;

import com.learnspring.taskmanager.dtos.TaskRequestDto;
import com.learnspring.taskmanager.model.Task;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {

    List<Task> findByUsername(String username);

    @Override
    <S extends Task> S save(S entity);

}

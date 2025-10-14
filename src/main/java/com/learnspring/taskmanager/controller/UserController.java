package com.learnspring.taskmanager.controller;

import com.learnspring.taskmanager.dtos.TaskRequestDto;
import com.learnspring.taskmanager.dtos.TaskResponseDto;
import com.learnspring.taskmanager.model.Task;
import com.learnspring.taskmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
        //Get all task
    @GetMapping("/task")
    public ResponseEntity<List<Task>> getUserTasks(){
            return ResponseEntity.ok(userService.getUserTasks());
    }

    //Create Task
    @PostMapping("/task")
    public ResponseEntity<TaskResponseDto> createUserTask(@RequestBody TaskRequestDto task){
        return new ResponseEntity<>(userService.craeteUserTask(task),HttpStatus.CREATED);
    }

    //Get specific Task
    @GetMapping("/task/{id}")
    public ResponseEntity<TaskResponseDto> getUserTask(@PathVariable String id){
        return ResponseEntity.ok(userService.getTaskById(id));
    }

    //update specific Task
    @PutMapping("/task/{id}")
    public ResponseEntity<TaskResponseDto> updateUserTask(@PathVariable String id, @RequestBody TaskRequestDto task){
        return new ResponseEntity<>(userService.updateTask(id,task),HttpStatus.OK);
    }

    //delete specific Task
    @DeleteMapping("/task/{id}")
    public ResponseEntity<Boolean> deleteUserTask(@PathVariable String id){
        return ResponseEntity.ok(userService.deleteUserTask(id));
    }

}

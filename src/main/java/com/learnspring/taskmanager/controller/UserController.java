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

    @GetMapping("/task")
    public ResponseEntity<List<Task>> getUserTasks(){
            return ResponseEntity.ok(userService.getUserTasks());
    }


    @PostMapping("/task")
    public ResponseEntity<TaskResponseDto> createUserTask(@RequestBody Task task){
        return new ResponseEntity<>(userService.craeteUserTask(task),HttpStatus.CREATED);
    }



    @GetMapping("/task/{id}")
    public ResponseEntity<Task> getUserTask(@PathVariable String id){
        return ResponseEntity.ok(userService.getTaskById(id));
    }


    @PutMapping("/task/{id}")
    public ResponseEntity<Task> updateUserTask(@PathVariable String id, @RequestBody Task task){
        return new ResponseEntity<>(userService.updateTask(id,task),HttpStatus.OK);
    }


    @DeleteMapping("/task/{id}")
    public ResponseEntity<Boolean> deleteUserTask(@PathVariable String id){
        return ResponseEntity.ok(userService.deleteUserTask(id));
    }

}

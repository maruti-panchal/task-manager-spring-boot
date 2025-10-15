package com.learnspring.taskmanager.controller;

import com.learnspring.taskmanager.model.Task;
import com.learnspring.taskmanager.repository.TaskRepository;
import com.learnspring.taskmanager.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public AdminController(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    // Get all tasks in system
    @GetMapping("/task")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskRepository.findAll());
    }

    // Get specific task by ID
    @GetMapping("/task/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable String id) {
        return ResponseEntity.ok(taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found")));
    }

    // Delete specific task by ID
    @DeleteMapping("/task/{id}")
    public ResponseEntity<Boolean> deleteTask(@PathVariable String id) {
        taskRepository.deleteById(id);
        return ResponseEntity.ok(true);
    }

    // Delete all tasks
    @DeleteMapping("/task")
    public ResponseEntity<Boolean> deleteAllTasks() {
        taskRepository.deleteAll();
        return ResponseEntity.ok(true);
    }

    // Delete any user by ID
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable String id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok(true);
    }
}

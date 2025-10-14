package com.learnspring.taskmanager.service;

import com.learnspring.taskmanager.dtos.TaskRequestDto;
import com.learnspring.taskmanager.dtos.TaskResponseDto;
import com.learnspring.taskmanager.model.Task;
import com.learnspring.taskmanager.model.User;
import com.learnspring.taskmanager.repository.TaskRepository;
import com.learnspring.taskmanager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;


    public UserService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;


    }


    public TaskResponseDto updateTask(String id, TaskRequestDto taskRequestDto) {
        // Step 1: Find existing task
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));

        // Step 2: Update fields only if new values are provided
        if (taskRequestDto.getTitle() != null) {
            existingTask.setTitle(taskRequestDto.getTitle());
        }
        if (taskRequestDto.getDescription() != null) {
            existingTask.setDescription(taskRequestDto.getDescription());
        }

        if (taskRequestDto.getDueDays() != 0) {
            existingTask.setDueDays(taskRequestDto.getDueDays());
        }

        Task updatedTask = taskRepository.save(existingTask);

        TaskResponseDto responseDto = new TaskResponseDto();
        responseDto.setTitle(updatedTask.getTitle());
        responseDto.setDescription(updatedTask.getDescription());
        responseDto.setStatus(updatedTask.getStatus());
        responseDto.setDueDays(updatedTask.getDueDays());
        responseDto.setCreatedBy(updatedTask.getCreatedBy());

        return responseDto;
    }


    public List<Task> getUserTasks(){
        return new ArrayList<>(taskRepository.findAll());
    }


    public boolean deleteUserTask(String id){
        taskRepository.deleteById(id);
        return true;
    }


    public TaskResponseDto getTaskById(String id){
        Optional<Task> task=taskRepository.findById(id);
        if(task.isPresent()){
            TaskResponseDto taskResponseDto=new TaskResponseDto();
            taskResponseDto.setTitle(task.get().getTitle());
            taskResponseDto.setDescription(task.get().getDescription());
            taskResponseDto.setStatus(task.get().getStatus());
            taskResponseDto.setCreatedBy(task.get().getCreatedBy());
            taskResponseDto.setDueDays(task.get().getDueDays());
            return taskResponseDto;
        }
        return null;
    }


    public TaskResponseDto craeteUserTask(TaskRequestDto taskRequestDto){
        User user=userRepository.findUserByUsername(taskRequestDto.getUsername());
        Task task = new Task();
        task=convertTaskRequestDtoToTask(taskRequestDto);
        task.setUser(user);
        return conversionTaskToTaskResponseDto(taskRepository.save(task));
    }

    public Task convertTaskRequestDtoToTask(TaskRequestDto taskRequestDto){
        Task task=new Task();
        task.setTitle(taskRequestDto.getTitle());
        task.setDescription(taskRequestDto.getDescription());
        task.setDueDays(taskRequestDto.getDueDays());
        task.setCreatedBy(taskRequestDto.getUsername());
        return task;
    }

    public TaskResponseDto conversionTaskToTaskResponseDto(Task taskmodel){
        TaskResponseDto taskResponseDto=new TaskResponseDto();
        taskResponseDto.setTitle(taskmodel.getTitle());
        taskResponseDto.setDescription(taskmodel.getDescription());
        taskResponseDto.setDueDays(taskmodel.getDueDays());
        taskResponseDto.setCreatedBy(taskmodel.getCreatedBy());
        return taskResponseDto;
    }


}

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


    public UserService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public Task updateTask(String id, Task usertask) {
       Task dbTask=taskRepository.findById(id).orElse(null);
        if(dbTask!=null){
            dbTask.setTitle(usertask.getTitle());
            dbTask.setDescription(usertask.getDescription());
            dbTask.setDueDays(usertask.getDueDays());
        }
        assert dbTask != null;
        return taskRepository.save(dbTask);
    }


    public List<Task> getUserTasks(){
        return new ArrayList<>(taskRepository.findAll());
    }


    public boolean deleteUserTask(String id){
        taskRepository.deleteById(id);
        return true;
    }


    public Task getTaskById(String id){
        return taskRepository.findById(id).orElse(null);

    }


    public Task craeteUserTask(Task task){
     return taskRepository.save(task);
    }


//
//    public Task convertTaskRequestDtoToTask(TaskRequestDto taskRequestDto){
//        Task task=new Task();
//        task.setTitle(taskRequestDto.getTitle());
//        task.setDescription(taskRequestDto.getDescription());
//        task.setDueDays(taskRequestDto.getDueDays());
//        task.setCreatedBy(taskRequestDto.getUsername());
//        return task;
//    }
//
//    public TaskResponseDto conversionTaskToTaskResponseDto(Task taskmodel){
//        TaskResponseDto taskResponseDto=new TaskResponseDto();
//        taskResponseDto.setTitle(taskmodel.getTitle());
//        taskResponseDto.setDescription(taskmodel.getDescription());
//        taskResponseDto.setDueDays(taskmodel.getDueDays());
//        taskResponseDto.setCreatedBy(taskmodel.getCreatedBy());
//        return taskResponseDto;
//    }


}

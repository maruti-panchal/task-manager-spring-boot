//package com.learnspring.taskmanager.service;
//
//import com.learnspring.taskmanager.dtos.TaskRequestDto;
//import com.learnspring.taskmanager.dtos.TaskResponseDto;
//import com.learnspring.taskmanager.model.Task;
//import com.learnspring.taskmanager.model.User;
//import com.learnspring.taskmanager.repository.TaskRepository;
//import com.learnspring.taskmanager.repository.UserRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserService {
//
//    private final TaskRepository taskRepository;
//    private final UserRepository userRepository;
//
//    public UserService(TaskRepository taskRepository, UserRepository userRepository) {
//        this.taskRepository = taskRepository;
//        this.userRepository = userRepository;
//    }
//
//
//    public Task updateTask(String id, Task usertask) {
//       Task dbTask=taskRepository.findById(id).orElse(null);
//        if(dbTask!=null){
//            dbTask.setTitle(usertask.getTitle());
//            dbTask.setDescription(usertask.getDescription());
//            dbTask.setDueDays(usertask.getDueDays());
//        }
//        assert dbTask != null;
//        return taskRepository.save(dbTask);
//    }
//
//
//    public List<Task> getUserTasks(){
//        return new ArrayList<>(taskRepository.findAll());
//    }
//
//
//    public boolean deleteUserTask(String id){
//        taskRepository.deleteById(id);
//        return true;
//    }
//
//
//    public Task getTaskById(String id){
//        return taskRepository.findById(id).orElse(null);
//
//    }
//
//
//    public TaskResponseDto craeteUserTask(Task task){
//        User user=userRepository.findUserByUsername(task.getUsername());
//        Task taskObj=taskRepository.save(task);
//        user.getTasks().add(taskObj);
//        userRepository.save(user);
//        return conversionTaskToTaskResponseDto(taskObj);
//
//    }
//
//
//    public TaskResponseDto conversionTaskToTaskResponseDto(Task taskmodel){
//        TaskResponseDto taskResponseDto=new TaskResponseDto();
//        taskResponseDto.setTitle(taskmodel.getTitle());
//        taskResponseDto.setDescription(taskmodel.getDescription());
//        taskResponseDto.setDueDays(taskmodel.getDueDays());
//        taskResponseDto.setId(taskmodel.getId().toString());
//        taskResponseDto.setUsername(taskmodel.getUsername());
//        return taskResponseDto;
//    }
//
//
//}


package com.learnspring.taskmanager.service;

import com.learnspring.taskmanager.dtos.TaskResponseDto;
import com.learnspring.taskmanager.model.Task;
import com.learnspring.taskmanager.model.User;
import com.learnspring.taskmanager.repository.TaskRepository;
import com.learnspring.taskmanager.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public UserService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public Task updateTask(String id, Task usertask) {
        Task dbTask = taskRepository.findById(id).orElse(null);
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        if (dbTask != null && dbTask.getUsername().equals(currentUsername)) {
            dbTask.setTitle(usertask.getTitle());
            dbTask.setDescription(usertask.getDescription());
            dbTask.setDueDays(usertask.getDueDays());
            return taskRepository.save(dbTask);
        }
        return null; // or throw exception for unauthorized
    }

    public List<Task> getUserTasks() {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Task> allTasks = taskRepository.findAll();
        List<Task> userTasks = new ArrayList<>();
        for (Task task : allTasks) {
            if (task.getUsername().equals(currentUsername)) {
                userTasks.add(task);
            }
        }
        return userTasks;
    }

    public boolean deleteUserTask(String id) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Task task = taskRepository.findById(id).orElse(null);

        if (task != null && task.getUsername().equals(currentUsername)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false; // or throw exception for unauthorized
    }

    public Task getTaskById(String id) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Task task = taskRepository.findById(id).orElse(null);

        if (task != null && task.getUsername().equals(currentUsername)) {
            return task;
        }
        return null; // or throw exception for unauthorized
    }

    public TaskResponseDto craeteUserTask(Task task) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUsername(currentUsername);

        task.setUsername(currentUsername);
        Task taskObj = taskRepository.save(task);

        user.getTasks().add(taskObj);
        userRepository.save(user);

        return conversionTaskToTaskResponseDto(taskObj);
    }

    public TaskResponseDto conversionTaskToTaskResponseDto(Task taskmodel) {
        TaskResponseDto taskResponseDto = new TaskResponseDto();
        taskResponseDto.setTitle(taskmodel.getTitle());
        taskResponseDto.setDescription(taskmodel.getDescription());
        taskResponseDto.setDueDays(taskmodel.getDueDays());
        taskResponseDto.setId(taskmodel.getId().toString());
        taskResponseDto.setUsername(taskmodel.getUsername());
        return taskResponseDto;
    }
}

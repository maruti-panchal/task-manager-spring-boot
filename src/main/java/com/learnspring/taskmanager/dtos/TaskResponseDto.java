package com.learnspring.taskmanager.dtos;

import com.learnspring.taskmanager.model.Status;
import com.learnspring.taskmanager.model.User;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
@Data
@Getter
@Setter
public class TaskResponseDto {
    private String title;
    private String description;
    private Status status;
    private int dueDays;
    private String createdBy;
    @DBRef
    private User user;
}

package com.learnspring.taskmanager.dtos;

import com.learnspring.taskmanager.model.Status;
import com.learnspring.taskmanager.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Getter
@Setter
public class TaskRequestDto {
    private String title;
    private String description;
    private int dueDays;
    private String username;

}

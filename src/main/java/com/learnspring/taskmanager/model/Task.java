package com.learnspring.taskmanager.model;


import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tasks")
@Data

public class Task {


    @Id
    private String id;

    private String title;

    private String description;
    private Status status;

    private int dueDays;
    private String createdBy;
    @DBRef(lazy = true)
    private User user;
}

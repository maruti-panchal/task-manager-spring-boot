package com.learnspring.taskmanager.model;


import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tasks")
@Data
public class Task {
    @Indexed(unique = true)
    private ObjectId id;
    @NonNull
    private String title;
    @NonNull
    private String description;
    private Status status;
    @NonNull
    private int dueDays;
    private String createdBy;
}

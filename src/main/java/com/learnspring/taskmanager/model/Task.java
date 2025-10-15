package com.learnspring.taskmanager.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "tasks")
@Data
@Getter
@Setter
public class Task {
    @Id
    private String id;
    private String title;
    private String description;
    private int dueDays;
}

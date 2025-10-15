package com.learnspring.taskmanager.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRequestDto {
    private String username;
    private String title;
    private String description;
    private int dueDays;
}

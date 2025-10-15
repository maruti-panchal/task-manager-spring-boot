package com.learnspring.taskmanager.dtos;

import lombok.*;

@Data
@Getter
@Setter
public class TaskResponseDto {
    private String title;
    private String description;
    private int dueDays;
}

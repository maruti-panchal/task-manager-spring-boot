package com.learnspring.taskmanager.dtos;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {
    @NonNull
    private String username;
    @NonNull
    private String password;
}

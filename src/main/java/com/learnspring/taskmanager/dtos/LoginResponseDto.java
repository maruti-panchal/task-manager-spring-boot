package com.learnspring.taskmanager.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponseDto {
    private String username;
    private String email;
    private String token;
}

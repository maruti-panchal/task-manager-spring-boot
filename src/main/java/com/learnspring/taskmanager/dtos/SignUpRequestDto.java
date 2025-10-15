package com.learnspring.taskmanager.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;

@Getter
@Setter
public class SignUpRequestDto {
    @Indexed(unique = true)
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private String role;
}

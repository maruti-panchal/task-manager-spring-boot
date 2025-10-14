package com.learnspring.taskmanager.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;

@Getter
@Setter
@Builder
public class SignUpRequestDto {
    @Indexed(unique = true)
    @NonNull
    private String username;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String email;
    @NonNull
    private String phone;
    @NonNull
    private String password;
    @NonNull
    private String role;
}

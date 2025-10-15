package com.learnspring.taskmanager.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
@Getter
@Setter
public class SignupResponseDto {
    private String id;
    private String username;
    private String email;
    private String phone;
    private String role;
}

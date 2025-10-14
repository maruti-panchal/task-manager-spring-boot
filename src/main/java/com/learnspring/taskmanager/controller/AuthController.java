package com.learnspring.taskmanager.controller;

import com.learnspring.taskmanager.dtos.LoginResponseDto;
import com.learnspring.taskmanager.dtos.SignUpRequestDto;
import com.learnspring.taskmanager.dtos.SignupResponseDto;
import com.learnspring.taskmanager.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    // Sign up
    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody SignUpRequestDto signUpRequestDto) {
        return ResponseEntity.ok(authService.signup(signUpRequestDto));
    }

    // Log in
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody SignUpRequestDto signUpRequestDto) {
        return ResponseEntity.ok(authService.login(signUpRequestDto));
    }
}

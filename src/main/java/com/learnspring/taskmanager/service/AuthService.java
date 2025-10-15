package com.learnspring.taskmanager.service;

import com.learnspring.taskmanager.dtos.LoginRequestDto;
import com.learnspring.taskmanager.dtos.LoginResponseDto;
import com.learnspring.taskmanager.dtos.SignUpRequestDto;
import com.learnspring.taskmanager.dtos.SignupResponseDto;
import com.learnspring.taskmanager.model.User;
import com.learnspring.taskmanager.repository.UserRepository;
import com.learnspring.taskmanager.security.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;

    }


    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    loginRequestDto.getUsername(),
                    loginRequestDto.getPassword() // raw password
            )
    );
    String token= JwtUtils.createJwtToken(loginRequestDto.getUsername());


    return LoginResponseDto.builder()
            .username(loginRequestDto.getUsername())
            .token(token)
            .build();
}


    public SignupResponseDto signup(SignUpRequestDto signUpRequestDto) {
        User user = convertsignUpRequestDtoToUser(signUpRequestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return convertUserToSignUpResponseDto(userRepository.save(user));
    }


    public User convertsignUpRequestDtoToUser(SignUpRequestDto signUpRequestDto) {
       // encode the password---->todo
        User user = new User();
        user.setUsername(signUpRequestDto.getUsername());
        user.setPassword(signUpRequestDto.getPassword());
        user.setEmail(signUpRequestDto.getEmail());
        user.setFirstName(signUpRequestDto.getFirstName());
        user.setLastName(signUpRequestDto.getLastName());
        user.setPhone(signUpRequestDto.getPhone());
        user.setRole(signUpRequestDto.getRole());
        return user;
    }

    public SignupResponseDto convertUserToSignUpResponseDto(User saveduser) {
        SignupResponseDto signupResponseDto=new SignupResponseDto();
        signupResponseDto.setId(saveduser.getId().toString());
        signupResponseDto.setUsername(saveduser.getUsername());
        signupResponseDto.setEmail(saveduser.getEmail());
        signupResponseDto.setPhone(saveduser.getPhone());
        signupResponseDto.setRole(saveduser.getRole());
        return signupResponseDto;
    }
}

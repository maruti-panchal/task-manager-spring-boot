package com.learnspring.taskmanager.service;

import com.learnspring.taskmanager.dtos.LoginResponseDto;
import com.learnspring.taskmanager.dtos.SignUpRequestDto;
import com.learnspring.taskmanager.dtos.SignupResponseDto;
import com.learnspring.taskmanager.model.User;
import com.learnspring.taskmanager.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoginResponseDto login(SignUpRequestDto signUpRequestDto) {
        User user=userRepository.findUserByUsername(signUpRequestDto.getUsername());
        if(user!=null){
            if(signUpRequestDto.getPassword().equals(user.getPassword())){
                LoginResponseDto loginResponseDto=new LoginResponseDto();
                loginResponseDto.setUsername(user.getUsername());
                loginResponseDto.setEmail(user.getEmail());
                loginResponseDto.setToken("AAAA");
                return loginResponseDto;
            }
            throw new RuntimeException("Wrong password");
        }
        return null;
    }

    public SignupResponseDto signup(SignUpRequestDto signUpRequestDto) {
        User user = convertsignUpRequestDtoToUser(signUpRequestDto);
        return convertUserToSignUpResponseDto(userRepository.save(user));
    }


    public User convertsignUpRequestDtoToUser(SignUpRequestDto signUpRequestDto) {
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
        signupResponseDto.setId(saveduser.getId());
        signupResponseDto.setUsername(saveduser.getUsername());
        signupResponseDto.setEmail(saveduser.getEmail());
        signupResponseDto.setPhone(saveduser.getPhone());
        signupResponseDto.setRole(saveduser.getRole());
        return signupResponseDto;
    }
}

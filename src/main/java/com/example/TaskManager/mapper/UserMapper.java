package com.example.TaskManager.mapper;

import org.springframework.stereotype.Component;

import com.example.TaskManager.entity.User;
import com.example.TaskManager.dto.user.CreateUserDTO;
import com.example.TaskManager.dto.user.UserResponseDTO;

@Component
public class UserMapper {

    public User toEntity(CreateUserDTO dto) {

        return new User(
            dto.getName(),
            dto.getEmail(),
            dto.getPassword(),
            dto.getRole()
        );
    }

    public UserResponseDTO toResponseDTO(User user) {

        return new UserResponseDTO(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getRole()
        );
    }
}

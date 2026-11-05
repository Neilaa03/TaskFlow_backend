package com.example.TaskManager.service;

import com.example.TaskManager.mapper.UserMapper;
import com.example.TaskManager.dto.user.UserResponseDTO;
import com.example.TaskManager.dto.user.CreateUserDTO;
import com.example.TaskManager.dto.user.UpdateUserDTO;

import org.springframework.stereotype.Service;
import com.example.TaskManager.repository.UserRepository;
import com.example.TaskManager.entity.User;
import java.util.List;

@Service 
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponseDTO)
                .toList();
    }

    // helper function
    private User findUserEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "User not found with id: " + id));
    }

    public UserResponseDTO getUserById(Long id) {
        //return userRepository.findById(id).orElse(null);
        User user = findUserEntityById(id);
        return userMapper.toResponseDTO(user);
    }

    public UserResponseDTO createUser(CreateUserDTO dto) {
        User user = userMapper.toEntity(dto);
        return userMapper.toResponseDTO(userRepository.save(user));
    }

    public UserResponseDTO updateUser(Long id, UpdateUserDTO updatedUser) {
        User toUpdate = findUserEntityById(id);

        toUpdate.setName(updatedUser.getName());
        toUpdate.setEmail(updatedUser.getEmail());
        toUpdate.setPassword(updatedUser.getPassword());

        return userMapper.toResponseDTO(userRepository.save(toUpdate));
    }

    public UserResponseDTO patchUser(Long id, UpdateUserDTO patchedUser) {
        User toUpdate = findUserEntityById(id);

        if (patchedUser.getName() != null && !patchedUser.getName().isBlank()) {
            toUpdate.setName(patchedUser.getName());
        }
        if (patchedUser.getEmail() != null && !patchedUser.getEmail().isBlank()) {
            toUpdate.setEmail(patchedUser.getEmail());
        }
        if (patchedUser.getPassword() != null && !patchedUser.getPassword().isBlank()) {
            toUpdate.setPassword(patchedUser.getPassword());
        }

        return userMapper.toResponseDTO(userRepository.save(toUpdate));
    }

    public void deleteUser(Long id) {
        User toDelete = findUserEntityById(id);
        userRepository.delete(toDelete);
    }
}

package com.example.TaskManager.service;

import com.example.TaskManager.mapper.UserMapper;
import com.example.TaskManager.dto.user.UserResponseDTO;
import com.example.TaskManager.dto.user.CreateUserDTO;
import com.example.TaskManager.dto.user.UpdateUserDTO;
import com.example.TaskManager.dto.user.PatchUserDTO;
import com.example.TaskManager.dto.project.ProjectResponseDTO;
import com.example.TaskManager.dto.task.TaskResponseDTO;
import com.example.TaskManager.mapper.ProjectMapper;
import com.example.TaskManager.mapper.TaskMapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.TaskManager.repository.UserRepository;
import com.example.TaskManager.entity.User;
import java.util.List;

@Service 
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ProjectMapper projectMapper;
    private final TaskMapper taskMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper,
                       ProjectMapper projectMapper, TaskMapper taskMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.projectMapper = projectMapper;
        this.taskMapper = taskMapper;
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
    public User getUserEntityById(Long id) {
        return findUserEntityById(id);
    }

    public UserResponseDTO getUserById(Long id) {
        //return userRepository.findById(id).orElse(null);
        User user = findUserEntityById(id);
        return userMapper.toResponseDTO(user);
    }

    @Transactional(readOnly = true)
    public List<ProjectResponseDTO> getUserProjects(Long id) {
        return findUserEntityById(id).getProjects().stream()
                .map(projectMapper::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<TaskResponseDTO> getUserTasks(Long id) {
        return findUserEntityById(id).getTasks().stream()
                .map(taskMapper::toResponseDTO)
                .toList();
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

    public UserResponseDTO patchUser(Long id, PatchUserDTO patchedUser) {
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
        if (patchedUser.getRole() != null) {
            toUpdate.setRole(patchedUser.getRole());
        }

        return userMapper.toResponseDTO(userRepository.save(toUpdate));
    }

    @Transactional
    public void deleteUser(Long id) {
        User toDelete = findUserEntityById(id);
        for (var project : List.copyOf(toDelete.getProjects())) {
            toDelete.removeProject(project);
        }
        for (var task : List.copyOf(toDelete.getTasks())) {
            toDelete.removeTask(task);
        }
        userRepository.delete(toDelete);
    }
}

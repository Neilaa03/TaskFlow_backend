package com.example.TaskManager.service;

import org.springframework.stereotype.Service;
import com.example.TaskManager.repository.UserRepository;
import com.example.TaskManager.entity.User;
import java.util.List;

@Service 
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        //return userRepository.findById(id).orElse(null);
        return userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User updatedUser) {
        User toUpdate = getUserById(id);

        toUpdate.setName(updatedUser.getName());
        toUpdate.setEmail(updatedUser.getEmail());
        toUpdate.setPassword(updatedUser.getPassword());

        return userRepository.save(toUpdate);
    }

    public User patchUser(Long id, User patchedUser) {
        User toUpdate = getUserById(id);

        if (patchedUser.getName() != null && !patchedUser.getName().isBlank()) {
            toUpdate.setName(patchedUser.getName());
        }
        if (patchedUser.getEmail() != null && !patchedUser.getEmail().isBlank()) {
            toUpdate.setEmail(patchedUser.getEmail());
        }
        if (patchedUser.getPassword() != null && !patchedUser.getPassword().isBlank()) {
            toUpdate.setPassword(patchedUser.getPassword());
        }
        
        return userRepository.save(toUpdate);
    }

    public void deleteUser(Long id) {
        User toDelete = getUserById(id);
        userRepository.delete(toDelete);
    }
}

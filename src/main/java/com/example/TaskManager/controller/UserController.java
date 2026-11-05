package com.example.TaskManager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.TaskManager.dto.user.CreateUserDTO;
import com.example.TaskManager.dto.user.UpdateUserDTO;
import com.example.TaskManager.dto.user.PatchUserDTO;
import com.example.TaskManager.dto.user.UserResponseDTO;
import com.example.TaskManager.service.UserService;

import java.util.List;



@RestController 
@RequestMapping ("/api/users")
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponseDTO> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    
    @PostMapping
    public UserResponseDTO postMethodName(@RequestBody CreateUserDTO user) {
        if (user.getName() == null || user.getName().isBlank()
            || user.getEmail() == null || user.getEmail().isBlank()
            || user.getPassword() == null || user.getPassword().isBlank()) 
        {
            throw new IllegalArgumentException("Fields cannot be empty");
        }           //isEmpty verifies "" isBlank verifies "" and "   "
        return userService.createUser(user);
    }       
    
    @PutMapping("/{id}")
    public UserResponseDTO updateUser(@PathVariable Long id, @RequestBody UpdateUserDTO user) {
        if (user.getName() == null || user.getName().isBlank()
            || user.getEmail() == null || user.getEmail().isBlank()
            || user.getPassword() == null || user.getPassword().isBlank()) 
        {
            throw new IllegalArgumentException("Fields cannot be empty");
        }   
        return userService.updateUser(id, user);
    }

    @PatchMapping("/{id}")
    public UserResponseDTO patchUser(@PathVariable Long id, @RequestBody PatchUserDTO user) {
        return userService.patchUser(id, user);
    }
   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    /*public void deleteUser(@PathVariable Long id) {
         userService.deleteUser(id);
    } */
    
}

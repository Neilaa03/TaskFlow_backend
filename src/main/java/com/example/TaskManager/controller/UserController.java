package com.example.TaskManager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.TaskManager.entity.User;
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
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    
    @PostMapping
    public User postMethodName(@RequestBody User user) {
        if (user.getName() == null || user.getName().isBlank()
            || user.getEmail() == null || user.getEmail().isBlank()
            || user.getPassword() == null || user.getPassword().isBlank()) 
        {
            throw new IllegalArgumentException("Fields cannot be empty");
        }           //isEmpty verifies "" isBlank verifies "" and "   "
        return userService.createUser(user);
    }       
    
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        if (user.getName() == null || user.getName().isBlank()
            || user.getEmail() == null || user.getEmail().isBlank()
            || user.getPassword() == null || user.getPassword().isBlank()) 
        {
            throw new IllegalArgumentException("Fields cannot be empty");
        }   
        return userService.updateUser(id, user);
    }

    @PatchMapping("/{id}")
    public User patchUser(@PathVariable Long id, @RequestBody User user) {
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
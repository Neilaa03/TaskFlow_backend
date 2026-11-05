package com.example.TaskManager.dto.user;

import com.example.TaskManager.entity.enums.UserRole;

public class UpdateUserDTO {

    private String name;
    private String email;
    private String password;
    private UserRole role;

    public UpdateUserDTO() {}

    // Getters and setters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
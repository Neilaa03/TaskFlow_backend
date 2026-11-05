package com.example.TaskManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.TaskManager.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}

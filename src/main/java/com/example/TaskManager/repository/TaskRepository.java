package com.example.TaskManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.TaskManager.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    
}

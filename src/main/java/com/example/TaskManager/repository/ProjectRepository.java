package com.example.TaskManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.TaskManager.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    
}

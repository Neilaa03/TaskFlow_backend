package com.example.TaskManager.controller;


import com.example.TaskManager.dto.project.CreateProjectDTO;
import com.example.TaskManager.dto.project.ProjectResponseDTO;
import com.example.TaskManager.dto.project.UpdateProjectDTO;
import com.example.TaskManager.service.ProjectService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<ProjectResponseDTO> getProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public ProjectResponseDTO getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @PostMapping
    public ProjectResponseDTO createProject(@RequestBody CreateProjectDTO project) {
        requireTitle(project.getTitle());
        return projectService.createProject(project);
    }

    @PutMapping("/{id}")
    public ProjectResponseDTO updateProject(@PathVariable Long id, @RequestBody UpdateProjectDTO project) {
        requireTitle(project.getTitle());
        return projectService.updateProject(id, project);
    }

    @PatchMapping("/{id}")
    public ProjectResponseDTO patchProject(@PathVariable Long id, @RequestBody UpdateProjectDTO project) {
        return projectService.patchProject(id, project);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    private void requireTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
    }
}

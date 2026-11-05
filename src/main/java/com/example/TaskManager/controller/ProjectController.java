package com.example.TaskManager.controller;


import com.example.TaskManager.entity.Project;
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
    public List<Project> getProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @PostMapping
    public Project createProject(@RequestBody Project project) {
        requireTitle(project);
        return projectService.createProject(project);
    }

    @PutMapping("/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody Project project) {
        requireTitle(project);
        return projectService.updateProject(id, project);
    }

    @PatchMapping("/{id}")
    public Project patchProject(@PathVariable Long id, @RequestBody Project project) {
        return projectService.patchProject(id, project);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    private void requireTitle(Project project) {
        if (project.getTitle() == null || project.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
    }
}

package com.example.TaskManager.controller;


import com.example.TaskManager.dto.project.CreateProjectDTO;
import com.example.TaskManager.dto.project.ProjectResponseDTO;
import com.example.TaskManager.dto.project.UpdateProjectDTO;
import com.example.TaskManager.dto.project.PatchProjectDTO;
import com.example.TaskManager.dto.task.TaskResponseDTO;
import com.example.TaskManager.dto.user.UserResponseDTO;
import com.example.TaskManager.service.ProjectService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;

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

    @GetMapping("/{id}/tasks")
    public List<TaskResponseDTO> getProjectTasks(@PathVariable Long id) {
        return projectService.getProjectTasks(id);
    }

    @GetMapping("/{id}/members")
    public List<UserResponseDTO> getProjectMembers(@PathVariable Long id) {
        return projectService.getProjectMembers(id);
    }

    @PostMapping
    public ProjectResponseDTO createProject(@Valid @RequestBody CreateProjectDTO project) {
        return projectService.createProject(project);
    }

    @PutMapping("/{id}")
    public ProjectResponseDTO updateProject(@PathVariable Long id, @Valid @RequestBody UpdateProjectDTO project) {
        return projectService.updateProject(id, project);
    }

    @PatchMapping("/{id}")
    public ProjectResponseDTO patchProject(@PathVariable Long id, @Valid @RequestBody PatchProjectDTO project) {
        return projectService.patchProject(id, project);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    // Member management endpoints
    @PostMapping("/{projectId}/members/{userId}")
    public ResponseEntity<Void> addMember(
            @PathVariable Long projectId,
            @PathVariable Long userId) {

        projectService.addMember(projectId, userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{projectId}/members/{userId}")
    public ResponseEntity<Void> removeMember(
            @PathVariable Long projectId,
            @PathVariable Long userId) {

        projectService.removeMember(projectId, userId);
        return ResponseEntity.noContent().build();
    }
}

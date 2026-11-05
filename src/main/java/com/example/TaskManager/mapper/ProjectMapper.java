package com.example.TaskManager.mapper;

import org.springframework.stereotype.Component;

import com.example.TaskManager.dto.project.CreateProjectDTO;
import com.example.TaskManager.dto.project.ProjectResponseDTO;
import com.example.TaskManager.dto.project.UpdateProjectDTO;
import com.example.TaskManager.dto.project.PatchProjectDTO;
import com.example.TaskManager.entity.Project;

@Component
public class ProjectMapper {

    public Project toEntity(CreateProjectDTO dto) {
        return new Project(dto.getTitle(), dto.getDescription());
    }

    public void updateEntity(Project project, UpdateProjectDTO dto) {
        if (dto.getTitle() != null) project.setTitle(dto.getTitle());
        if (dto.getDescription() != null) project.setDescription(dto.getDescription());
    }

    public void patchEntity(Project project, PatchProjectDTO dto) {
        if (dto.getTitle() != null && !dto.getTitle().isBlank()) project.setTitle(dto.getTitle());
        if (dto.getDescription() != null) project.setDescription(dto.getDescription());
    }

    public ProjectResponseDTO toResponseDTO(Project project) {
        return new ProjectResponseDTO(project.getId(), project.getTitle(),
                project.getDescription(), project.getCreatedAt());
    }
}

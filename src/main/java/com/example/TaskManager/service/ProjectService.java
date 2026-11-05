package com.example.TaskManager.service;

import org.springframework.stereotype.Service;
import com.example.TaskManager.repository.ProjectRepository;
import com.example.TaskManager.entity.Project;
import com.example.TaskManager.mapper.ProjectMapper;
import com.example.TaskManager.dto.project.CreateProjectDTO;
import com.example.TaskManager.dto.project.UpdateProjectDTO;
import com.example.TaskManager.dto.project.ProjectResponseDTO;
import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public ProjectService(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    public List<ProjectResponseDTO> getAllProjects() {
        return projectRepository.findAll().stream().map(projectMapper::toResponseDTO).toList();
    }

    private Project findProjectEntityById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
    }

    public ProjectResponseDTO getProjectById(Long id) {
        return projectMapper.toResponseDTO(findProjectEntityById(id));
    }

    public ProjectResponseDTO createProject(CreateProjectDTO dto) {
        Project project = projectMapper.toEntity(dto);
        return projectMapper.toResponseDTO(projectRepository.save(project));
    }

    public ProjectResponseDTO updateProject(Long id, UpdateProjectDTO dto) {
        Project project = findProjectEntityById(id);
        projectMapper.updateEntity(project, dto);
        return projectMapper.toResponseDTO(projectRepository.save(project));
    }

    public ProjectResponseDTO patchProject(Long id, UpdateProjectDTO dto) {
        return updateProject(id, dto);
    }

    public void deleteProject(Long id) {
        projectRepository.delete(findProjectEntityById(id));
    }
}

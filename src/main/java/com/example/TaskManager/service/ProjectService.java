package com.example.TaskManager.service;

import org.springframework.stereotype.Service;
import com.example.TaskManager.repository.ProjectRepository;
import org.springframework.transaction.annotation.Transactional;
import com.example.TaskManager.entity.Project;
import com.example.TaskManager.entity.User;
import com.example.TaskManager.dto.task.TaskResponseDTO;
import com.example.TaskManager.dto.user.UserResponseDTO;
import com.example.TaskManager.mapper.TaskMapper;
import com.example.TaskManager.mapper.UserMapper;

import com.example.TaskManager.mapper.ProjectMapper;
import com.example.TaskManager.dto.project.CreateProjectDTO;
import com.example.TaskManager.dto.project.UpdateProjectDTO;
import com.example.TaskManager.dto.project.ProjectResponseDTO;
import com.example.TaskManager.dto.project.PatchProjectDTO;
import java.util.List;

@Service
public class ProjectService {

    private final ProjectMapper projectMapper;
    private final ProjectRepository projectRepository;
    private final UserService userService;  
    private final TaskMapper taskMapper;
    private final UserMapper userMapper;

    public ProjectService(
        ProjectRepository projectRepository, 
        ProjectMapper projectMapper, 
        UserService userService,
        TaskMapper taskMapper,
        UserMapper userMapper)
    {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
        this.userService = userService;
        this.taskMapper = taskMapper;
        this.userMapper = userMapper;
    }

    public List<ProjectResponseDTO> getAllProjects() {
        return projectRepository.findAll().stream().map(projectMapper::toResponseDTO).toList();
    }

    private Project findProjectEntityById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
    }

    public Project getProjectEntityById(Long id) {
        return findProjectEntityById(id);
    }

    public ProjectResponseDTO getProjectById(Long id) {
        return projectMapper.toResponseDTO(findProjectEntityById(id));
    }

    @Transactional(readOnly = true)
    public List<TaskResponseDTO> getProjectTasks(Long id) {
        return findProjectEntityById(id).getTasks().stream()
                .map(taskMapper::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<UserResponseDTO> getProjectMembers(Long id) {
        return findProjectEntityById(id).getUsers().stream()
                .map(userMapper::toResponseDTO)
                .toList();
    }

    public ProjectResponseDTO createProject(CreateProjectDTO dto) {
        Project project = projectMapper.toEntity(dto);
        return projectMapper.toResponseDTO(projectRepository.save(project));
    }

    @Transactional
    @Transactional
    public void addMember(Long projectId, Long userId) {

        Project project = findProjectEntityById(projectId);

        User user = userService.getUserEntityById(userId);

        project.addUser(user);  //updates both sides of the relationship
    }

    @Transactional
    @Transactional
    public void removeMember(Long projectId, Long userId) {

        Project project = findProjectEntityById(projectId);

        User user = userService.getUserEntityById(userId);

        project.removeUser(user);
    }

    public ProjectResponseDTO updateProject(Long id, UpdateProjectDTO dto) {
        Project project = findProjectEntityById(id);
        projectMapper.updateEntity(project, dto);
        return projectMapper.toResponseDTO(projectRepository.save(project));
    }

    public ProjectResponseDTO patchProject(Long id, PatchProjectDTO dto) {
        Project project = findProjectEntityById(id);
        projectMapper.patchEntity(project, dto);
        return projectMapper.toResponseDTO(projectRepository.save(project));
    }

    @Transactional
    public void deleteProject(Long id) {
        Project project = findProjectEntityById(id);
        for (User user : List.copyOf(project.getUsers())) {
            project.removeUser(user);
        }
        projectRepository.delete(project);
    }
}

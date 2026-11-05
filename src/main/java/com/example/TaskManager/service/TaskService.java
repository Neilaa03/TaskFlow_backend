package com.example.TaskManager.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.TaskManager.entity.Project;
import com.example.TaskManager.entity.User;
import com.example.TaskManager.entity.enums.TaskStatus;
import com.example.TaskManager.repository.TaskRepository;
import com.example.TaskManager.entity.Task;
import com.example.TaskManager.mapper.TaskMapper;
import com.example.TaskManager.dto.task.CreateTaskDTO;
import com.example.TaskManager.dto.task.UpdateTaskDTO;
import com.example.TaskManager.dto.task.TaskResponseDTO;
import com.example.TaskManager.dto.task.PatchTaskDTO;
import java.util.List;

@Service 
public class TaskService {
    
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final ProjectService projectService;
    private final UserService userService;

    public TaskService(
        TaskRepository taskRepository,
        TaskMapper taskMapper, 
        ProjectService projectService, 
        UserService userService) 
    {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.projectService = projectService;
        this.userService = userService;
    }

    public List<TaskResponseDTO> getAllTasks() {
        return taskRepository.findAll().stream().map(taskMapper::toResponseDTO).toList();
    }

    private Task findTaskEntityById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }

    public TaskResponseDTO getTaskById(Long id) {
        return taskMapper.toResponseDTO(findTaskEntityById(id));
    }

    @Transactional
    public TaskResponseDTO createTask(CreateTaskDTO dto) {

        // 1. Convert DTO into entity
        Task task = taskMapper.toEntity(dto);
        task.setStatus(TaskStatus.PENDING);

        // 2. Retrieve and associate project
        Project project = projectService.getProjectEntityById(dto.getProjectId());
        project.addTask(task); 

        // 3. Retrieve and associate user if provided
        if (dto.getUserId() != null) {
            User user = userService.getUserEntityById(dto.getUserId());
            user.addTask(task);
        }

        // 4. Save and return response DTO
        Task savedTask = taskRepository.save(task);

        return taskMapper.toResponseDTO(savedTask);
    }

    @Transactional
    public TaskResponseDTO updateTask(Long id, UpdateTaskDTO dto) {
        Task task = findTaskEntityById(id);
        taskMapper.updateEntity(task, dto);
        changeProject(task, dto.getProjectId());
        changeUser(task, dto.getUserId());
        return taskMapper.toResponseDTO(taskRepository.save(task));
    }

    @Transactional
    public TaskResponseDTO patchTask(Long id, PatchTaskDTO dto) {
        Task task = findTaskEntityById(id);
        taskMapper.patchEntity(task, dto);
        if (dto.getProjectId() != null) changeProject(task, dto.getProjectId());
        if (dto.getUserId() != null) changeUser(task, dto.getUserId());
        else if (Boolean.TRUE.equals(dto.getUnassignUser())) changeUser(task, null);
        return taskMapper.toResponseDTO(taskRepository.save(task));
    }

    private void changeProject(Task task, Long projectId) {
        Project newProject = projectService.getProjectEntityById(projectId);
        Project oldProject = task.getProject();
        if (oldProject == newProject) return;
        if (oldProject != null) oldProject.removeTask(task);
        newProject.addTask(task);
    }

    private void changeUser(Task task, Long userId) {
        User oldUser = task.getUser();
        User newUser = userId == null ? null : userService.getUserEntityById(userId);
        if (oldUser == newUser) return;
        if (oldUser != null) oldUser.removeTask(task);
        if (newUser != null) newUser.addTask(task);
        else task.setUser(null);
    }

    @Transactional
    public void deleteTask(Long id) {
        Task task = findTaskEntityById(id);
        if (task.getProject() != null) task.getProject().removeTask(task);
        if (task.getUser() != null) task.getUser().removeTask(task);
        taskRepository.delete(task);
    }
}

package com.example.TaskManager.service;

import org.springframework.stereotype.Service;
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

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
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

    public TaskResponseDTO createTask(CreateTaskDTO dto) {
        Task task = taskMapper.toEntity(dto);
        return taskMapper.toResponseDTO(taskRepository.save(task));
    }

    public TaskResponseDTO updateTask(Long id, UpdateTaskDTO dto) {
        Task task = findTaskEntityById(id);
        taskMapper.updateEntity(task, dto);
        return taskMapper.toResponseDTO(taskRepository.save(task));
    }

    public TaskResponseDTO patchTask(Long id, PatchTaskDTO dto) {
        Task task = findTaskEntityById(id);
        taskMapper.patchEntity(task, dto);
        return taskMapper.toResponseDTO(taskRepository.save(task));
    }

    public void deleteTask(Long id) {
        taskRepository.delete(findTaskEntityById(id));
    }
}

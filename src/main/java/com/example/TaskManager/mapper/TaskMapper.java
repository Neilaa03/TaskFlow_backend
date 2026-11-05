package com.example.TaskManager.mapper;

import org.springframework.stereotype.Component;

import com.example.TaskManager.dto.task.CreateTaskDTO;
import com.example.TaskManager.dto.task.TaskResponseDTO;
import com.example.TaskManager.dto.task.UpdateTaskDTO;
import com.example.TaskManager.dto.task.PatchTaskDTO;
import com.example.TaskManager.entity.Task;

@Component
public class TaskMapper {

    public Task toEntity(CreateTaskDTO dto) {
        return new Task(dto.getTitle(), dto.getDescription(),
                dto.getPriority(), dto.getDueDate());
    }

    public void updateEntity(Task task, UpdateTaskDTO dto) {
        if (dto.getTitle() != null) task.setTitle(dto.getTitle());
        if (dto.getDescription() != null) task.setDescription(dto.getDescription());
        if (dto.getStatus() != null) task.setStatus(dto.getStatus());
        if (dto.getPriority() != null) task.setPriority(dto.getPriority());
        if (dto.getDueDate() != null) task.setDueDate(dto.getDueDate());
    }

    public void patchEntity(Task task, PatchTaskDTO dto) {
        if (dto.getTitle() != null && !dto.getTitle().isBlank()) task.setTitle(dto.getTitle());
        if (dto.getDescription() != null) task.setDescription(dto.getDescription());
        if (dto.getStatus() != null) task.setStatus(dto.getStatus());
        if (dto.getPriority() != null) task.setPriority(dto.getPriority());
        if (dto.getDueDate() != null) task.setDueDate(dto.getDueDate());
    }

    public TaskResponseDTO toResponseDTO(Task task) {
        return new TaskResponseDTO(task.getId(), task.getTitle(), task.getDescription(),
                task.getStatus(), task.getPriority(), task.getDueDate(),
                task.getProject() == null ? null : task.getProject().getId(),
                task.getUser() == null ? null : task.getUser().getId());
    }
}

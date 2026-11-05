package com.example.TaskManager.controller;

import com.example.TaskManager.dto.task.CreateTaskDTO;
import com.example.TaskManager.dto.task.TaskResponseDTO;
import com.example.TaskManager.dto.task.UpdateTaskDTO;
import com.example.TaskManager.dto.task.PatchTaskDTO;
import com.example.TaskManager.service.TaskService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskResponseDTO> getTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskResponseDTO getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    public TaskResponseDTO createTask(@RequestBody CreateTaskDTO task) {
        validateTask(task, true);
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    public TaskResponseDTO updateTask(@PathVariable Long id, @RequestBody UpdateTaskDTO task) {
        validateTask(task, true);
        return taskService.updateTask(id, task);
    }

    @PatchMapping("/{id}")
    public TaskResponseDTO patchTask(@PathVariable Long id, @RequestBody PatchTaskDTO task) {
        return taskService.patchTask(id, task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    private void validateTask(CreateTaskDTO task, boolean requireAll) {
        if (task.getTitle() == null || task.getTitle().isBlank()
                || (requireAll && (task.getStatus() == null || task.getPriority() == null))) {
            throw new IllegalArgumentException("Title, status, and priority are required");
        }
    }

    private void validateTask(UpdateTaskDTO task, boolean requireAll) {
        if (task.getTitle() == null || task.getTitle().isBlank()
                || (requireAll && (task.getStatus() == null || task.getPriority() == null))) {
            throw new IllegalArgumentException("Title, status, and priority are required");
        }
    }
}

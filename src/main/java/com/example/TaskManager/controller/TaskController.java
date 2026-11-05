package com.example.TaskManager.controller;

import com.example.TaskManager.dto.task.CreateTaskDTO;
import com.example.TaskManager.dto.task.TaskResponseDTO;
import com.example.TaskManager.dto.task.UpdateTaskDTO;
import com.example.TaskManager.dto.task.PatchTaskDTO;
import com.example.TaskManager.service.TaskService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;

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
    public TaskResponseDTO createTask(@Valid @RequestBody CreateTaskDTO task) {
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    public TaskResponseDTO updateTask(@PathVariable Long id, @Valid @RequestBody UpdateTaskDTO task) {
        return taskService.updateTask(id, task);
    }

    @PatchMapping("/{id}")
    public TaskResponseDTO patchTask(@PathVariable Long id, @Valid @RequestBody PatchTaskDTO task) {
        return taskService.patchTask(id, task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

}

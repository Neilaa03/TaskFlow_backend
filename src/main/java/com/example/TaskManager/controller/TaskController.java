package com.example.TaskManager.controller;

import com.example.TaskManager.entity.Task;
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
    public List<Task> getTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        validateTask(task, true);
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        validateTask(task, true);
        return taskService.updateTask(id, task);
    }

    @PatchMapping("/{id}")
    public Task patchTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.patchTask(id, task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    private void validateTask(Task task, boolean requireAll) {
        if (task.getTitle() == null || task.getTitle().isBlank()
                || (requireAll && (task.getStatus() == null || task.getPriority() == null))) {
            throw new IllegalArgumentException("Title, status, and priority are required");
        }
    }
}

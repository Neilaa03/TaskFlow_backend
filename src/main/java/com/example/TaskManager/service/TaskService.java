package com.example.TaskManager.service;

import org.springframework.stereotype.Service;
import com.example.TaskManager.repository.TaskRepository;
import com.example.TaskManager.entity.Task;
import java.util.List;

@Service 
public class TaskService {
    
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task updatedTask) {
        Task task = getTaskById(id);
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setStatus(updatedTask.getStatus());
        task.setPriority(updatedTask.getPriority());
        task.setDueDate(updatedTask.getDueDate());
        return taskRepository.save(task);
    }

    public Task patchTask(Long id, Task patchedTask) {
        Task task = getTaskById(id);
        if (patchedTask.getTitle() != null && !patchedTask.getTitle().isBlank()) {
            task.setTitle(patchedTask.getTitle());
        }
        if (patchedTask.getDescription() != null) task.setDescription(patchedTask.getDescription());
        if (patchedTask.getStatus() != null) task.setStatus(patchedTask.getStatus());
        if (patchedTask.getPriority() != null) task.setPriority(patchedTask.getPriority());
        if (patchedTask.getDueDate() != null) task.setDueDate(patchedTask.getDueDate());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.delete(getTaskById(id));
    }
}

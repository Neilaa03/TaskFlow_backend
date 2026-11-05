package com.example.TaskManager.dto.task;

import java.time.LocalDate;
import com.example.TaskManager.entity.enums.Priority;
import com.example.TaskManager.entity.enums.TaskStatus;

public class TaskResponseDTO {

    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private Priority priority;
    private LocalDate dueDate;
    private Long projectId;
    private Long userId;

    public TaskResponseDTO() {}

    public TaskResponseDTO(Long id, String title, String description, TaskStatus status,
                           Priority priority, LocalDate dueDate, Long projectId, Long userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.dueDate = dueDate;
        this.projectId = projectId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public Priority getPriority() {
        return priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public Long getProjectId() { return projectId; }
    public Long getUserId() { return userId; }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setProjectId(Long projectId) { this.projectId = projectId; }
    public void setUserId(Long userId) { this.userId = userId; }
}

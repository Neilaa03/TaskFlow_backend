package com.example.TaskManager.dto.task;

import java.time.LocalDate;
import com.example.TaskManager.entity.enums.Priority;
import com.example.TaskManager.entity.enums.TaskStatus;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Positive;

public class PatchTaskDTO {

    @Pattern(regexp = ".*\\S.*", message = "Title cannot be blank")
    @Size(max = 150, message = "Title must be at most 150 characters")
    private String title;

    @Size(max = 2000, message = "Description must be at most 2000 characters")
    private String description;

    @Positive(message = "Project ID must be positive")
    private Long projectId;

    @Positive(message = "User ID must be positive")
    private Long userId;

    private Boolean unassignUser;
    private TaskStatus status;
    private Priority priority;
    private LocalDate dueDate;

    public PatchTaskDTO() {}

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public TaskStatus getStatus() { return status; }
    public Priority getPriority() { return priority; }
    public LocalDate getDueDate() { return dueDate; }
    public Long getProjectId() { return projectId; }
    public Long getUserId() { return userId; }
    public Boolean getUnassignUser() { return unassignUser; }

    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setStatus(TaskStatus status) { this.status = status; }
    public void setPriority(Priority priority) { this.priority = priority; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public void setUnassignUser(Boolean unassignUser) { this.unassignUser = unassignUser; }
}

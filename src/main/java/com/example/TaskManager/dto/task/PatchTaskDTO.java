package com.example.TaskManager.dto.task;

import java.time.LocalDate;
import com.example.TaskManager.entity.enums.Priority;
import com.example.TaskManager.entity.enums.TaskStatus;

public class PatchTaskDTO {

    private String title;
    private String description;
    private TaskStatus status;
    private Priority priority;
    private LocalDate dueDate;

    public PatchTaskDTO() {}

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public TaskStatus getStatus() { return status; }
    public Priority getPriority() { return priority; }
    public LocalDate getDueDate() { return dueDate; }

    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setStatus(TaskStatus status) { this.status = status; }
    public void setPriority(Priority priority) { this.priority = priority; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
}

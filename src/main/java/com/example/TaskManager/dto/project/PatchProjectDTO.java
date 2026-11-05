package com.example.TaskManager.dto.project;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class PatchProjectDTO {

    @Pattern(regexp = ".*\\S.*", message = "Title cannot be blank")
    @Size(max = 150, message = "Title must be at most 150 characters")
    private String title;

    @Size(max = 2000, message = "Description must be at most 2000 characters")
    private String description;

    public PatchProjectDTO() {}

    public String getTitle() { return title; }
    public String getDescription() { return description; }

    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
}

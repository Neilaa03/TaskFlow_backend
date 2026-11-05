package com.example.TaskManager.dto.project;

public class UpdateProjectDTO {

    private String title;
    private String description;

    public UpdateProjectDTO() {}

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

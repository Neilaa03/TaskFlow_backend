package com.example.TaskManager.dto.project;

public class CreateProjectDTO {

    private String title;
    private String description;

    public CreateProjectDTO() {}

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

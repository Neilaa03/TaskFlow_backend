package com.example.TaskManager.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity 
@Table(name="projects") 
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    private void setCreatedAtIfMissing() {
        if (createdAt == null) createdAt = LocalDateTime.now();
    }

    public Project() {}

    public Project(String title, String description) {
        this.title = title;
        this.description = description;
        this.createdAt = LocalDateTime.now();
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}

package com.example.TaskManager.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;


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

    // One-to-Many: A project can contain multiple tasks
    @OneToMany(mappedBy= "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

    // Many-to-Many: A project can have multiple users
    @ManyToMany(mappedBy = "projects")      // refers to the "projects" field in the User entity
    private Set<User> users = new HashSet<>();

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

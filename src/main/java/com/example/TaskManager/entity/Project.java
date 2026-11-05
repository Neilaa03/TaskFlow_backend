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
    @OneToMany(mappedBy= "project", cascade = CascadeType.ALL)
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

    // Task management methods
    public void addTask(Task task) {
        if (task == null) return;
        if (!tasks.contains(task)) tasks.add(task);
        task.setProject(this);
    }

    public void removeTask(Task task) {
        if (task == null) return;
        tasks.remove(task);
        if (task.getProject() == this) task.setProject(null);
    }

    // User management methods
    public Set<User> getUsers() {
        return users;
    }
    
    public void addUser(User user) {
        if (user == null) return;
        users.add(user);
        if (!user.getProjects().contains(this)) user.getProjects().add(this);
    }

    public void removeUser(User user) {
        if (user == null) return;
        users.remove(user);
        user.getProjects().remove(this);
    }

    public List<Task> getTasks() {
        return tasks;
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

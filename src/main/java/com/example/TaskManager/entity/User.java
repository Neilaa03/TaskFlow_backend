package com.example.TaskManager.entity;

import com.example.TaskManager.entity.enums.UserRole;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;


@Entity 
@Table(name="users")
public class User {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String name;
    
    @Column (nullable = false, unique = true)
    private String email;
    
    @Column (nullable = false)
    private String password;

    @Enumerated (EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    // Many-to-Many: A user can participate in multiple projects
    @ManyToMany 
    @JoinTable(
        name = "user_projects",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private Set<Project> projects = new HashSet<>();

    // One-to-Many: A user can be assigned multiple tasks
    @OneToMany(mappedBy = "user")
    private List<Task> tasks = new ArrayList<>();       //list conserves order

        //mappedby refers to the field name of the other entity
    public User() {}

    public User(String name, String email, String password, UserRole role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    //Project management methods
    public Set<Project> getProjects() {
        return projects;
    }
    public void addProject(Project project) {
        if (project == null) return;
        projects.add(project);
        if (!project.getUsers().contains(this)) project.getUsers().add(this);
    }

    public void removeProject(Project project) {
        if (project == null) return;
        projects.remove(project);
        if (project.getUsers().contains(this)) project.getUsers().remove(this);
    }

    //Task management methods
    public List<Task> getTasks() {
        return tasks;
    }
    public void addTask(Task task) {
        if (task == null) return;
        if (!tasks.contains(task)) tasks.add(task);
        task.setUser(this);
    }
    public void removeTask(Task task) {
        if (task == null) return;
        tasks.remove(task);
        if (task.getUser() == this) task.setUser(null);
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

}

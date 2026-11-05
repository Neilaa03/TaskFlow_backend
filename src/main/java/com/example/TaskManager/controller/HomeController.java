package com.example.TaskManager.controller;

import org.springframework.web.bind.annotation.*;


@RestController 
@RequestMapping("/api")
public class HomeController {
    @GetMapping("/home")
    public String hello() {
        return "Welcome to Task manager";
    }
    
    @GetMapping("/info")
    public String info() {
        return "Random Info ...";
    }
    
}

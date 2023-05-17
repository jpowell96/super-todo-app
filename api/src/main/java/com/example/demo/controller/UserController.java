package com.example.demo.controller;

import com.example.demo.request.CreateUserRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {


    @PostMapping
    public void createUser(CreateUserRequest request) {
        // Pass it off to the service
    }
}


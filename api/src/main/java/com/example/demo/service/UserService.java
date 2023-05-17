package com.example.demo.service;

import com.example.demo.model.ApplicationUser;
import com.example.demo.request.CreateUserRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    // TODO: Add a depdency for a PasswordEncoder


    public UserService() {
    }

    public ApplicationUser createUser(CreateUserRequest createUserRequest) {
        // 1. Validation that username is not already claimed
        return null;
    }

    public ApplicationUser getApplicationUserByUserName(String username) {
        return null;
    }


}

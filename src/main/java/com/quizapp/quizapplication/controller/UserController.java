package com.quizapp.quizapplication.controller;

import com.quizapp.quizapplication.model.Users;
import com.quizapp.quizapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Users register(@RequestBody Users user){
        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody Users user){
        return userService.Verify(user);
    }
}
package com.example.springsecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springsecurity.service.UserService;
import com.example.springsecurity.model.Users;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<Users> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/add")
    public String addUser(@RequestBody Users user) {
        userService.addUser(user);
        return "User added successfully";
    }

}
package com.example.springsecurity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.springsecurity.model.Users;
import com.example.springsecurity.repository.UserDetailsRepo;

@Service
public class UserService {

    @Autowired
    UserDetailsRepo userDetailsRepo;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    public void addUser(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDetailsRepo.save(user);
    }

    public List<Users> getUsers() {
        return userDetailsRepo.findAll();
    }
}
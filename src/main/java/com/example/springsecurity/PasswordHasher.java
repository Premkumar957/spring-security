package com.example.springsecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHasher {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
    String password = "Premkumar@97";
    String hashedPassword = passwordEncoder.encode(password);
}
package com.example.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class HelloController {
    
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World";
    }

    @GetMapping("/")
    public String home(HttpServletRequest request) {
        return "Home Page" + request.getSession().getId();
    }

    @GetMapping("/about")
    public String aboutUs() {
        return "Premkumar learning Springboot";
    }

    @GetMapping("/quote")
    public String sayMotivation() {
        return "Become the programmer you are meant to be!";
    }

}

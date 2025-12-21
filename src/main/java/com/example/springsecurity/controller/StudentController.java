package com.example.springsecurity.controller;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springsecurity.model.Student;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class StudentController {

    List<Student> students = new ArrayList<>(
        Arrays.asList(
            new Student(1, "Premkumar", "AI"),
            new Student(2, "Izack Ark", "SAP")
        )
    );
    
    @GetMapping("/students")
    public List<Student> getStudents() {
        return students;
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/students")
    public void addStudent(@RequestBody Student student) {
        students.add(student);
    } 
}

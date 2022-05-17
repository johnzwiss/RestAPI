package com.example.demo.student.controller;
import java.util.List;

import com.example.demo.student.data.model.Student;
import com.example.demo.student.service.impl.StudentServiceImpl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping(path = "api/v1/student")


public class StudentController {

    private final StudentServiceImpl studentService;

    @Autowired
    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }



    @GetMapping
	public List<Student> getStudents() {
		return studentService.getStudentsLastNameDesc(); 
	}
    
}

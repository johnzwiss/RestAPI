package com.example.demo.student.controller;
import java.util.List;
import java.util.Optional;

import com.example.demo.student.data.model.Student;
import com.example.demo.student.dto.StudentDto;
import com.example.demo.student.service.StudentService;
import com.example.demo.student.service.impl.StudentServiceImpl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path = "api/v1/students")


public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }



    @GetMapping
	public List<StudentDto> getStudents() {
		return studentService.getStudentsLastNameDesc(); 
	}

    @GetMapping("/{id}")
    public StudentDto getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @GetMapping("/delete/{id}")
    public Boolean deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

    @GetMapping("/create")
    public Boolean createStudent(@RequestBody StudentDto requestedStudent) {
        return studentService.createStudent(requestedStudent);
    }
    
}

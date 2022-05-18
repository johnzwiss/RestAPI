package com.example.demo.student.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.student.data.model.Student;
import com.example.demo.student.dto.StudentDto;


public interface StudentService {
    Optional<Student> getStudent(Long id);
    List<Student> getStudentsLastNameDesc();
    Boolean createStudent(StudentDto requestedStudent);
}
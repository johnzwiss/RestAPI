package com.example.demo.student.dto.mapper;

import com.example.demo.student.data.model.Student;
import com.example.demo.student.dto.StudentDto;

public class StudentMapper {
    public static StudentDto studentToStudentDto(Student student) {
        return StudentDto
                .builder()
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .age(student.getAge())
                .build();

    }

    public static Student studentDtoToStudent(StudentDto studentDto) {
        Student newStudent = new Student();
        newStudent.setFirstName(studentDto.getFirstName());
        newStudent.setLastName(studentDto.getLastName());
        newStudent.setAge(studentDto.getAge());
        return newStudent;
    }

}

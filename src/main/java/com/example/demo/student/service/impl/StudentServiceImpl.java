package com.example.demo.student.service.impl;

import java.util.List;
import java.util.Optional;

import com.example.demo.student.data.model.Student;
import com.example.demo.student.data.repository.StudentRepository;
import com.example.demo.student.dto.StudentDto;
import com.example.demo.student.dto.mapper.StudentMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class StudentServiceImpl {

	private final StudentRepository studentRepository;

	@Autowired
	public StudentServiceImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public Optional<Student> getStudent(Long id) {
		return studentRepository.findById(id);
	}

	public List<Student> getStudentsLastNameDesc() {
		return studentRepository.findAllOrderByLastNameDesc();
	}

	public Boolean createStudent(StudentDto requestedStudent) {
		Optional<Student> existingStudent = studentRepository
				.findByFirstNameAndByLastName(requestedStudent.getFirstName(), requestedStudent.getLastName());
		if (existingStudent.isPresent()) {
			return false;
		}

		Student newStudent = StudentMapper.studentDtoToStudent(requestedStudent);
		studentRepository.save(newStudent);
		
		return true;
	}
}

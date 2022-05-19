package com.example.demo.student.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.print.attribute.standard.MediaSize.Other;

import com.example.demo.student.data.model.Student;
import com.example.demo.student.data.repository.StudentRepository;
import com.example.demo.student.dto.StudentDto;
import com.example.demo.student.dto.mapper.StudentMapper;
import com.example.demo.student.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;

	public StudentDto getStudent(Long id) {
		return studentRepository.findById((id)).map(StudentMapper::studentToStudentDto).orElse(null);
	}

	public List<StudentDto> getStudentsLastNameDesc() {
		return studentRepository
		.findAllByOrderByLastName()
		.stream()
		.map(StudentMapper::studentToStudentDto)
		.collect(Collectors.toList());
	}

	public Boolean createStudent(StudentDto requestedStudent) {
		Optional<Student> existingStudent = studentRepository
				.findByFirstNameAndLastName(requestedStudent.getFirstName(), requestedStudent.getLastName());
		if (existingStudent.isPresent()) {
			return false;
		}

		Student newStudent = StudentMapper.studentDtoToStudent(requestedStudent);
		studentRepository.save(newStudent);

		return true;
	}

	public Boolean deleteStudent(Long id) {
		if (studentRepository.existsById(id)) {
			studentRepository.deleteById(id);
			return true;
		}
		return false;

	}

}

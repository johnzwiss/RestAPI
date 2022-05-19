package com.example.demo.student.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.student.data.model.Student;
import com.example.demo.student.data.repository.StudentRepository;
import com.example.demo.student.dto.StudentDto;
import com.example.demo.student.dto.mapper.StudentMapper;
import com.example.demo.student.service.StudentService;

import org.springframework.stereotype.Component;

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
		.findAllByOrderByLastNameDesc()
		.stream()
		.map(StudentMapper::studentToStudentDto)
		.collect(Collectors.toList());
	}

	public Boolean createStudent(StudentDto requestedStudent) {
		if(studentRepository.existsByFirstNameAndLastName(requestedStudent.getFirstName(), requestedStudent.getLastName())) {
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

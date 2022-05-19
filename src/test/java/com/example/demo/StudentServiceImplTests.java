package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import com.example.demo.student.data.model.Student;
import com.example.demo.student.data.repository.StudentRepository;
import com.example.demo.student.dto.StudentDto;
import com.example.demo.student.service.StudentService;
import com.example.demo.student.service.impl.StudentServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

public class StudentServiceImplTests {
	@Mock
	StudentRepository studentRepositoryMock;

	@InjectMocks
	StudentServiceImpl studentService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetStudents() {
		var student1 = new Student();
		student1.setFirstName("test_first_name_1");
		student1.setLastName("test_last_name_1");
		student1.setAge(10);

		var student2 = new Student();
		student2.setFirstName("test_first_name_2");
		student2.setLastName("z_test_last_name_2");
		student2.setAge(20);

		var students = Arrays.asList(student1, student2);

		when(studentRepositoryMock.findAllByOrderByLastNameDesc()).thenReturn(students);

		var results = studentService.getStudentsLastNameDesc();

		assertFalse(results.isEmpty());
		assertEquals(results.size(), 2);
		assertEquals(results.get(0).getFirstName(), "test_first_name_1");
		assertEquals(results.get(0).getLastName(), "test_last_name_1");
		assertEquals(results.get(0).getAge(), 10);

		assertEquals(results.get(1).getFirstName(), "test_first_name_2");
		assertEquals(results.get(1).getLastName(), "z_test_last_name_2");
		assertEquals(results.get(1).getAge(), 20);

		verify(studentRepositoryMock, times(1)).findAllByOrderByLastNameDesc();
	}

	@Test
	public void testSaveStudentFails() {
		var studentDto = StudentDto.builder()
				.firstName("test_first_name_1")
				.lastName("test_last_name_1")
				.age(10)
				.build();

		when(studentRepositoryMock.existsByFirstNameAndLastName(eq("test_first_name_1"), eq("test_last_name_1")))
				.thenReturn(true);

		var results = studentService.createStudent(studentDto);

		assertFalse(results);

		verify(studentRepositoryMock, times(1)).existsByFirstNameAndLastName(eq("test_first_name_1"),
				eq("test_last_name_1"));
		verify(studentRepositoryMock, never()).save(any(Student.class));
	}

	@Test
	public void testSaveStudentSucceeds() {
		var studentDto = StudentDto.builder()
				.firstName("test_first_name_1")
				.lastName("test_last_name_1")
				.age(10)
				.build();

		when(studentRepositoryMock.existsByFirstNameAndLastName(eq("test_first_name_1"), eq("test_last_name_1")))
				.thenReturn(false);
		when(studentRepositoryMock.save(any(Student.class))).thenReturn(new Student());

		var results = studentService.createStudent(studentDto);

		assertTrue(results);

		verify(studentRepositoryMock, times(1)).existsByFirstNameAndLastName(eq("test_first_name_1"),
				eq("test_last_name_1"));
		verify(studentRepositoryMock, times(1)).save(any(Student.class));
	}

	@Test
	public void testDeleteStudentFails() {
		var testId = 1L; 

		when(studentRepositoryMock.existsById(eq(testId))).thenReturn(false);

		var results = studentService.deleteStudent(testId);

		assertFalse(results);

		verify(studentRepositoryMock, times(1)).existsById(eq(testId));
		verify(studentRepositoryMock, never()).deleteById(eq(testId));

	}

	@Test
	public void testDeleteStudentSucceeds() {
		var testId = 1L;

		when(studentRepositoryMock.existsById(eq(testId))).thenReturn(true);
		doNothing().when(studentRepositoryMock).deleteById(eq(testId));

		var results = studentService.deleteStudent(testId);

		assertTrue(results);

		verify(studentRepositoryMock, times(1)).existsById(eq(testId));
		verify(studentRepositoryMock, times(1)).deleteById(eq(testId));

	}
}

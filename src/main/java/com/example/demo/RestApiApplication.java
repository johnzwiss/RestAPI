package com.example.demo;

import java.util.List;

import com.example.demo.student.Student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}
	
	// @GetMapping
	// public List<Student> getStudent() {
	// 	return List.of(
	// 		new Student(
	// 			1L,
	// 			"John",
	// 			10
	// 		)
	// 	);
	// }

}

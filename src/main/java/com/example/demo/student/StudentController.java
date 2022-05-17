package com.example.demo.student;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping(path = "api/v1/student")


public class StudentController {

    @GetMapping
	public List<Student> getStudent() {
		return List.of(
			new Student(
				1L,
				"John",
				10
			)
		);
	}
    
}

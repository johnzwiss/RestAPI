package com.example.demo.student.dto;

// import lombok.AccessLevel;
// import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class StudentDto {
    private String firstName;
    private String lastName;
    private Integer age;
}

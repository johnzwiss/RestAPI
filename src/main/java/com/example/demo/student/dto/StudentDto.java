package com.example.demo.student.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(access = AccessLevel.PUBLIC)
public class StudentDto {
    private String firstName;
    private String lastName;
    private Integer age;
}

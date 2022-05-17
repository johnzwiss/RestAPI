package com.example.demo.student.data.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.student.data.model.Student;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    List<Student> findAllOrderByLastNameDesc();   
    Optional<Student> findByFirstNameAndByLastName();
}

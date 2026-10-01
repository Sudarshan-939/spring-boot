package com.suda.studentapi.repository;

import com.suda.studentapi.entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository
        extends JpaRepository<Student, Integer> {

    boolean existsByRegisterNo(String registerNo);

    List<Student> findByNameContainingIgnoreCase(
            String name
    );

    List<Student> findByRegisterNoContainingIgnoreCase(
            String registerNo
    );

    List<Student> findByYear(Integer year);

    List<Student> findBySemester(Integer semester);
}
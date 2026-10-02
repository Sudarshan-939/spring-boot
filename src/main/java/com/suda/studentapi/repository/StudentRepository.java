package com.suda.studentapi.repository;

import com.suda.studentapi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository
        extends JpaRepository<Student, Integer> {

    Optional<Student> findByRegisterNo(String registerNo);

    boolean existsByRegisterNo(String registerNo);

    List<Student> findByDepartmentId(String departmentId);
}
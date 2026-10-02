package com.suda.studentapi.controller;

import com.suda.studentapi.dto.StudentRequest;
import com.suda.studentapi.dto.StudentResponse;
import com.suda.studentapi.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    // =====================================================
    // GET ALL STUDENTS
    // =====================================================

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents() {

        return ResponseEntity.ok(
                studentService.getAllStudents()
        );
    }


    // =====================================================
    // GET STUDENT BY ID
    // =====================================================

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                studentService.getStudentById(id)
        );
    }


    // =====================================================
    // CREATE STUDENT
    // =====================================================

    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(
            @Valid @RequestBody StudentRequest request) {

        StudentResponse response =
                studentService.createStudent(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    // =====================================================
    // UPDATE STUDENT
    // =====================================================

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(
            @PathVariable Integer id,
            @Valid @RequestBody StudentRequest request) {

        StudentResponse response =
                studentService.updateStudent(id, request);

        return ResponseEntity.ok(response);
    }


    // =====================================================
    // DELETE STUDENT
    // =====================================================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(
            @PathVariable Integer id) {

        studentService.deleteStudent(id);

        return ResponseEntity.noContent().build();
    }
}
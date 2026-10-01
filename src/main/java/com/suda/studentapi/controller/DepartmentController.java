package com.suda.studentapi.controller;

import com.suda.studentapi.dto.DepartmentRequest;
import com.suda.studentapi.dto.DepartmentResponse;
import com.suda.studentapi.dto.StudentResponse;
import com.suda.studentapi.services.DepartmentService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // GET all departments
    @GetMapping
    public ResponseEntity<List<DepartmentResponse>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    // GET department by ID
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> getDepartmentById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                departmentService.getDepartmentById(id)
        );
    }

    // POST department
    @PostMapping
    public ResponseEntity<DepartmentResponse> createDepartment(
            @Valid @RequestBody DepartmentRequest request) {

        DepartmentResponse response =
                departmentService.createDepartment(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // PUT department
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponse> updateDepartment(
            @PathVariable Integer id,
            @Valid @RequestBody DepartmentRequest request) {

        return ResponseEntity.ok(
                departmentService.updateDepartment(id, request)
        );
    }

    // DELETE department
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(
            @PathVariable Integer id) {

        departmentService.deleteDepartment(id);

        return ResponseEntity.noContent().build();
    }

    // GET students belonging to department
    @GetMapping("/{id}/students")
    public ResponseEntity<List<StudentResponse>> getDepartmentStudents(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                departmentService.getDepartmentStudents(id)
        );
    }
}
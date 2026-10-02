package com.suda.studentapi.services;

import com.suda.studentapi.dto.DepartmentRequest;
import com.suda.studentapi.dto.DepartmentResponse;
import com.suda.studentapi.dto.StudentResponse;
import com.suda.studentapi.entity.Department;
import com.suda.studentapi.exception.DepartmentNotFoundException;
import com.suda.studentapi.repository.DepartmentRepository;
import com.suda.studentapi.repository.StudentRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
        private final StudentRepository studentRepository;

        public DepartmentService(
                        DepartmentRepository departmentRepository,
                        StudentRepository studentRepository) {
        this.departmentRepository = departmentRepository;
                this.studentRepository = studentRepository;
    }

    // GET all
    @Transactional(readOnly = true)
    public List<DepartmentResponse> getAllDepartments() {

        return departmentRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // GET by ID
    @Transactional(readOnly = true)
    public DepartmentResponse getDepartmentById(Integer id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new DepartmentNotFoundException(
                                "Department not found with id: " + id
                        )
                );

        return toResponse(department);
    }

    // POST
    public DepartmentResponse createDepartment(
            DepartmentRequest request) {

        Department department = new Department();

        department.setDepartmentCode(
                request.getDepartmentCode()
        );

        department.setDepartmentName(
                request.getDepartmentName()
        );

        Department saved = departmentRepository.save(department);

        return toResponse(saved);
    }

    // PUT
    public DepartmentResponse updateDepartment(
            Integer id,
            DepartmentRequest request) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new DepartmentNotFoundException(
                                "Department not found with id: " + id
                        )
                );

        department.setDepartmentCode(
                request.getDepartmentCode()
        );

        department.setDepartmentName(
                request.getDepartmentName()
        );

        return toResponse(departmentRepository.save(department));
    }

    // DELETE
    public void deleteDepartment(Integer id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new DepartmentNotFoundException(
                                "Department not found with id: " + id
                        )
                );

        departmentRepository.delete(department);
    }

    // Students belonging to department
    @Transactional(readOnly = true)
    public List<StudentResponse> getDepartmentStudents(Integer id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new DepartmentNotFoundException(
                                "Department not found with id: " + id
                        )
                );

        return studentRepository.findByDepartmentId(
                        department.getDepartmentCode()
                )
                .stream()
                .map(StudentResponse::new)
                .toList();
    }

    private DepartmentResponse toResponse(
            Department department) {

        DepartmentResponse response =
                new DepartmentResponse();

        response.setId(department.getId());
        response.setDepartmentCode(
                department.getDepartmentCode()
        );
        response.setDepartmentName(
                department.getDepartmentName()
        );

        return response;
    }
}
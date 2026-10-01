package com.suda.studentapi.services;

import com.suda.studentapi.dto.StudentRequest;
import com.suda.studentapi.dto.StudentResponse;
import com.suda.studentapi.entity.Department;
import com.suda.studentapi.entity.Student;
import com.suda.studentapi.exception.DuplicateRegisterNumberException;
import com.suda.studentapi.exception.StudentNotFoundException;
import com.suda.studentapi.repository.DepartmentRepository;
import com.suda.studentapi.repository.StudentRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;

    public StudentService(
            StudentRepository studentRepository,
            DepartmentRepository departmentRepository) {

        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
    }

    // GET ALL
    @Transactional(readOnly = true)
    public List<StudentResponse> getAllStudents() {

        return studentRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // GET BY ID
    @Transactional(readOnly = true)
    public StudentResponse getStudentById(Integer id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found with id: " + id
                        )
                );

        return toResponse(student);
    }

    // POST
    public StudentResponse createStudent(
            StudentRequest request) {

        if (studentRepository.existsByRegisterNo(
                request.getRegisterNo())) {

            throw new DuplicateRegisterNumberException(
                    "Register number already exists: "
                            + request.getRegisterNo()
            );
        }

        Department department =
                findDepartment(request.getDepartment());

        Student student = new Student();

        student.setRegisterNo(request.getRegisterNo());
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setPhone(request.getPhone());
        student.setDepartment(department);
        student.setYear(request.getYear());
        student.setSemester(request.getSemester());

        Student saved = studentRepository.save(student);

        return toResponse(saved);
    }

    // PUT
    public StudentResponse updateStudent(
            Integer id,
            StudentRequest request) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found with id: " + id
                        )
                );

        if (!student.getRegisterNo()
                .equals(request.getRegisterNo())
                &&
                studentRepository.existsByRegisterNo(
                        request.getRegisterNo())) {

            throw new DuplicateRegisterNumberException(
                    "Register number already exists: "
                            + request.getRegisterNo()
            );
        }

        Department department =
                findDepartment(request.getDepartment());

        student.setRegisterNo(request.getRegisterNo());
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setPhone(request.getPhone());
        student.setDepartment(department);
        student.setYear(request.getYear());
        student.setSemester(request.getSemester());

        return toResponse(studentRepository.save(student));
    }

    // DELETE
    public void deleteStudent(Integer id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found with id: " + id
                        )
                );

        studentRepository.delete(student);
    }

    private Department findDepartment(String code) {

        return departmentRepository
                .findByDepartmentCode(code)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Department not found: " + code
                        )
                );
    }

    private StudentResponse toResponse(Student student) {

        StudentResponse response =
                new StudentResponse();

        response.setId(student.getId());
        response.setRegisterNo(student.getRegisterNo());
        response.setName(student.getName());
        response.setEmail(student.getEmail());
        response.setPhone(student.getPhone());
        response.setYear(student.getYear());
        response.setSemester(student.getSemester());

        if (student.getDepartment() != null) {

            response.setDepartment(
                    student.getDepartment()
                            .getDepartmentCode()
            );

            response.setDepartmentName(
                    student.getDepartment()
                            .getDepartmentName()
            );
        }

        return response;
    }
}
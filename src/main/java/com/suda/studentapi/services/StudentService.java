package com.suda.studentapi.services;

import com.suda.studentapi.dto.StudentRequest;
import com.suda.studentapi.dto.StudentResponse;
import com.suda.studentapi.entity.Student;
import com.suda.studentapi.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;


    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    // =====================================================
    // GET ALL STUDENTS
    // =====================================================

    public List<StudentResponse> getAllStudents() {

        return studentRepository.findAll()
                .stream()
                .map(StudentResponse::new)
                .toList();
    }


    // =====================================================
    // GET STUDENT BY ID
    // =====================================================

    public StudentResponse getStudentById(Integer id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Student not found with ID: " + id
                        )
                );

        return new StudentResponse(student);
    }


    // =====================================================
    // CREATE STUDENT
    // =====================================================

    public StudentResponse createStudent(StudentRequest request) {

        if (studentRepository.existsByRegisterNo(
                request.getRegisterNo())) {

            throw new RuntimeException(
                    "Student already exists with register number: "
                            + request.getRegisterNo()
            );
        }


        Student student = new Student();

        student.setRegisterNo(request.getRegisterNo());
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setPhone(request.getPhone());
        student.setDepartmentId(request.getDepartmentId());
        student.setYear(request.getYear());
        student.setSemester(request.getSemester());


        Student savedStudent =
                studentRepository.save(student);

        return new StudentResponse(savedStudent);
    }


    // =====================================================
    // UPDATE STUDENT
    // =====================================================

    public StudentResponse updateStudent(
            Integer id,
            StudentRequest request) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Student not found with ID: " + id
                        )
                );


        student.setRegisterNo(request.getRegisterNo());
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setPhone(request.getPhone());
        student.setDepartmentId(request.getDepartmentId());
        student.setYear(request.getYear());
        student.setSemester(request.getSemester());


        Student updatedStudent =
                studentRepository.save(student);

        return new StudentResponse(updatedStudent);
    }


    // =====================================================
    // DELETE STUDENT
    // =====================================================

    public void deleteStudent(Integer id) {

        if (!studentRepository.existsById(id)) {

            throw new RuntimeException(
                    "Student not found with ID: " + id
            );
        }

        studentRepository.deleteById(id);
    }
}
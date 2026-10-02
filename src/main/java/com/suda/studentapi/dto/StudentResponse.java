package com.suda.studentapi.dto;

import com.suda.studentapi.entity.Student;

public class StudentResponse {

    private Integer id;
    private String registerNo;
    private String name;
    private String email;
    private String phone;
    private String departmentId;
    private Integer year;
    private Integer semester;


    public StudentResponse() {
    }


    public StudentResponse(Student student) {

        this.id = student.getId();
        this.registerNo = student.getRegisterNo();
        this.name = student.getName();
        this.email = student.getEmail();
        this.phone = student.getPhone();
        this.departmentId = student.getDepartmentId();
        this.year = student.getYear();
        this.semester = student.getSemester();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getRegisterNo() {
        return registerNo;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }


    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }


    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }
}
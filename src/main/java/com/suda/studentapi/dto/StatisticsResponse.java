package com.suda.studentapi.dto;

import java.util.Map;

public class StatisticsResponse {

    private long totalStudents;
    private Map<String, Long> departmentWise;
    private Map<Integer, Long> yearWise;

    public StatisticsResponse() {
    }

    public StatisticsResponse(
            long totalStudents,
            Map<String, Long> departmentWise,
            Map<Integer, Long> yearWise) {

        this.totalStudents = totalStudents;
        this.departmentWise = departmentWise;
        this.yearWise = yearWise;
    }

    public long getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(long totalStudents) {
        this.totalStudents = totalStudents;
    }

    public Map<String, Long> getDepartmentWise() {
        return departmentWise;
    }

    public void setDepartmentWise(Map<String, Long> departmentWise) {
        this.departmentWise = departmentWise;
    }

    public Map<Integer, Long> getYearWise() {
        return yearWise;
    }

    public void setYearWise(Map<Integer, Long> yearWise) {
        this.yearWise = yearWise;
    }
}
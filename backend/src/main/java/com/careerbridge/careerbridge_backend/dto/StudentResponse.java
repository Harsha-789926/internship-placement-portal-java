
package com.careerbridge.careerbridge_backend.dto;

import java.math.BigDecimal;

public class StudentResponse {

    private Integer studentId;
    private String name;
    private String email;
    private BigDecimal cgpa;
    private String resumePath;
    private Integer branchId;

    public StudentResponse() {
    }

    public StudentResponse(Integer studentId, String name, String email,
                           BigDecimal cgpa, String resumePath, Integer branchId) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.cgpa = cgpa;
        this.resumePath = resumePath;
        this.branchId = branchId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public BigDecimal getCgpa() {
        return cgpa;
    }

    public String getResumePath() {
        return resumePath;
    }

    public Integer getBranchId() {
        return branchId;
    }
}

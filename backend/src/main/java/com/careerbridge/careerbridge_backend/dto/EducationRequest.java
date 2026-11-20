
package com.careerbridge.careerbridge_backend.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class EducationRequest {

    @NotNull(message = "Student ID is required")
    private Integer studentId;

    @NotBlank(message = "Education level is required")
    @Size(max = 100)
    private String educationLevel;

    @NotBlank(message = "Institution is required")
    @Size(max = 100)
    private String institution;

    @DecimalMin(value = "0.0")
    @DecimalMax(value = "100.0")
    private BigDecimal percentage;

    @DecimalMin(value = "0.0")
    @DecimalMax(value = "10.0")
    private BigDecimal cgpa;

    @NotNull(message = "Passing year is required")
    @Min(value = 1900)
    @Max(value = 2100)
    private Integer passingYear;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    public BigDecimal getCgpa() {
        return cgpa;
    }

    public void setCgpa(BigDecimal cgpa) {
        this.cgpa = cgpa;
    }

    public Integer getPassingYear() {
        return passingYear;
    }

    public void setPassingYear(Integer passingYear) {
        this.passingYear = passingYear;
    }
}

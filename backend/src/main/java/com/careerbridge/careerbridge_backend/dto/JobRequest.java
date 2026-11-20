
package com.careerbridge.careerbridge_backend.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class JobRequest {

    @NotNull(message = "Job ID is required")
    @Positive(message = "Job ID must be positive")
    private Integer jobId;

    @NotNull(message = "Company ID is required")
    @Positive(message = "Company ID must be positive")
    private Integer companyId;

    @NotBlank(message = "Job title is required")
    @Size(max = 100)
    private String title;

    @Size(max = 200)
    private String description;

    @Size(max = 100)
    private String packageAmount;

    @Size(max = 200)
    private String location;

    @DecimalMin(value = "0.0")
    @DecimalMax(value = "10.0")
    private BigDecimal minimumCgpa;

    @NotNull(message = "Application deadline is required")
    @FutureOrPresent(message = "Deadline cannot be in the past")
    private LocalDate deadline;

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPackageAmount() {
        return packageAmount;
    }

    public void setPackageAmount(String packageAmount) {
        this.packageAmount = packageAmount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getMinimumCgpa() {
        return minimumCgpa;
    }

    public void setMinimumCgpa(BigDecimal minimumCgpa) {
        this.minimumCgpa = minimumCgpa;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}

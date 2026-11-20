
package com.careerbridge.careerbridge_backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "JOB")
public class Job {

    @Id
    @Column(name = "job_id")
    private Integer jobId;

    @Column(name = "company_id", nullable = false)
    private Integer companyId;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "description", length = 200)
    private String description;

    @Column(name = "package", length = 100)
    private String packageAmount;

    @Column(name = "location", length = 200)
    private String location;

    @Column(name = "minimum_cgpa", precision = 4, scale = 2)
    private BigDecimal minimumCgpa;

    @Column(name = "deadline", nullable = false)
    private LocalDate deadline;

    public Job() {
    }

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

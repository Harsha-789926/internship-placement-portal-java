
package com.careerbridge.careerbridge_backend.controller;

import com.careerbridge.careerbridge_backend.dto.JobRequest;
import com.careerbridge.careerbridge_backend.dto.JobResponse;
import com.careerbridge.careerbridge_backend.service.JobService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    // Create a new job
    @PostMapping
    public ResponseEntity<JobResponse> createJob(
            @Valid @RequestBody JobRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(jobService.createJob(request));
    }

    // Get all jobs
    @GetMapping
    public ResponseEntity<List<JobResponse>> getAllJobs() {

        return ResponseEntity.ok(jobService.getAllJobs());
    }

    // Get job by ID
    @GetMapping("/{jobId}")
    public ResponseEntity<JobResponse> getJobById(
            @PathVariable Integer jobId) {

        return ResponseEntity.ok(jobService.getJobById(jobId));
    }

    // Get jobs posted by a company
    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<JobResponse>> getJobsByCompany(
            @PathVariable Integer companyId) {

        return ResponseEntity.ok(
                jobService.getJobsByCompany(companyId));
    }

    // Update a job
    @PutMapping("/{jobId}")
    public ResponseEntity<JobResponse> updateJob(
            @PathVariable Integer jobId,
            @Valid @RequestBody JobRequest request) {

        return ResponseEntity.ok(
                jobService.updateJob(jobId, request));
    }

    // Delete a job
    @DeleteMapping("/{jobId}")
    public ResponseEntity<String> deleteJob(
            @PathVariable Integer jobId) {

        jobService.deleteJob(jobId);

        return ResponseEntity.ok("Job deleted successfully");
    }
}

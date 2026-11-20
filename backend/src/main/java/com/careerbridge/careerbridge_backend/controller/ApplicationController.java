
package com.careerbridge.careerbridge_backend.controller;

import com.careerbridge.careerbridge_backend.dto.ApplicationRequest;
import com.careerbridge.careerbridge_backend.dto.ApplicationResponse;
import com.careerbridge.careerbridge_backend.service.ApplicationService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    // Student applies for a job
    @PostMapping
    public ResponseEntity<ApplicationResponse> applyForJob(
            @Valid @RequestBody ApplicationRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(applicationService.applyForJob(request));
    }

    // Get all applications submitted by a student
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ApplicationResponse>> getApplicationsByStudent(
            @PathVariable Integer studentId) {

        return ResponseEntity.ok(
                applicationService.getApplicationsByStudent(studentId));
    }

    // Get all applicants for a particular job
    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<ApplicationResponse>> getApplicationsByJob(
            @PathVariable Integer jobId) {

        return ResponseEntity.ok(
                applicationService.getApplicationsByJob(jobId));
    }
}

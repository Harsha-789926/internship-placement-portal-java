
package com.careerbridge.careerbridge_backend.controller;

import com.careerbridge.careerbridge_backend.dto.EducationRequest;
import com.careerbridge.careerbridge_backend.dto.EducationResponse;
import com.careerbridge.careerbridge_backend.service.EducationService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/education")
public class EducationController {

    private final EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    // Add education record
    @PostMapping
    public ResponseEntity<EducationResponse> addEducation(
            @Valid @RequestBody EducationRequest request) {

        EducationResponse response = educationService.addEducation(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // View education records of a student
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<EducationResponse>> getEducationByStudent(
            @PathVariable Integer studentId) {

        return ResponseEntity.ok(
                educationService.getEducationByStudent(studentId));
    }

    // Update education record
    @PutMapping("/{educationId}")
    public ResponseEntity<EducationResponse> updateEducation(
            @PathVariable Integer educationId,
            @Valid @RequestBody EducationRequest request) {

        return ResponseEntity.ok(
                educationService.updateEducation(educationId, request));
    }

    // Delete education record
    @DeleteMapping("/{educationId}")
    public ResponseEntity<String> deleteEducation(
            @PathVariable Integer educationId) {

        educationService.deleteEducation(educationId);

        return ResponseEntity.ok("Education record deleted successfully");
    }
}

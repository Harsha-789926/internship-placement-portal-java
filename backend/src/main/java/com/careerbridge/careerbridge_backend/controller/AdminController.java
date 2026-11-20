
package com.careerbridge.careerbridge_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.careerbridge.careerbridge_backend.dto.AdminLoginRequest;
import com.careerbridge.careerbridge_backend.dto.AdminLoginResponse;
import com.careerbridge.careerbridge_backend.dto.CompanyResponse;
import com.careerbridge.careerbridge_backend.dto.StudentResponse;
import com.careerbridge.careerbridge_backend.dto.ApplicationResponse;

import com.careerbridge.careerbridge_backend.service.AdminService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Admin Login
    @PostMapping("/login")
    public ResponseEntity<AdminLoginResponse> login(
            @Valid @RequestBody AdminLoginRequest request) {

        AdminLoginResponse response = adminService.login(request);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // View All Companies
    @GetMapping("/companies")
    public ResponseEntity<List<CompanyResponse>> getAllCompanies() {

        return ResponseEntity.ok(adminService.getAllCompanies());
    }

    // Verify Company
    @PutMapping("/companies/{companyId}/verify")
    public ResponseEntity<CompanyResponse> verifyCompany(
            @PathVariable Integer companyId) {

        return ResponseEntity.ok(adminService.verifyCompany(companyId));
    }

    // Reject Company
    @PutMapping("/companies/{companyId}/reject")
    public ResponseEntity<CompanyResponse> rejectCompany(
            @PathVariable Integer companyId) {

        return ResponseEntity.ok(adminService.rejectCompany(companyId));
    }

    // View All Students
    @GetMapping("/students")
    public ResponseEntity<List<StudentResponse>> getAllStudents() {

        return ResponseEntity.ok(adminService.getAllStudents());
    }

    // Delete Student
    @DeleteMapping("/students/{studentId}")
    public ResponseEntity<String> deleteStudent(
            @PathVariable Integer studentId) {

        return ResponseEntity.ok(adminService.deleteStudent(studentId));
    }

    // View All Applications
    @GetMapping("/applications")
    public ResponseEntity<List<ApplicationResponse>> getAllApplications() {

        return ResponseEntity.ok(adminService.getAllApplications());
    }

    // Update Application Status
    @PutMapping("/applications/{applicationId}/status")
    public ResponseEntity<ApplicationResponse> updateApplicationStatus(
            @PathVariable Integer applicationId,
            @RequestBody String status) {

        return ResponseEntity.ok(
                adminService.updateApplicationStatus(applicationId, status)
        );
    }
}

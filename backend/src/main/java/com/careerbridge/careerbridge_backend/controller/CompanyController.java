
package com.careerbridge.careerbridge_backend.controller;

import com.careerbridge.careerbridge_backend.dto.CompanyRegistrationRequest;
import com.careerbridge.careerbridge_backend.dto.CompanyLoginRequest;
import com.careerbridge.careerbridge_backend.dto.CompanyResponse;
import com.careerbridge.careerbridge_backend.service.CompanyService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    // Register a company
    @PostMapping("/register")
    public ResponseEntity<CompanyResponse> registerCompany(
            @Valid @RequestBody CompanyRegistrationRequest request) {

        CompanyResponse response =
                companyService.registerCompany(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Company login
    @PostMapping("/login")
    public ResponseEntity<CompanyResponse> loginCompany(
            @Valid @RequestBody CompanyLoginRequest request) {

        return ResponseEntity.ok(
                companyService.loginCompany(request));
    }

    // View company profile
    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyResponse> getCompanyById(
            @PathVariable Integer companyId) {

        return ResponseEntity.ok(
                companyService.getCompanyById(companyId));
    }

    // Update company profile
    @PutMapping("/{companyId}")
    public ResponseEntity<CompanyResponse> updateCompany(
            @PathVariable Integer companyId,
            @Valid @RequestBody CompanyRegistrationRequest request) {

        return ResponseEntity.ok(
                companyService.updateCompany(companyId, request));
    }
}

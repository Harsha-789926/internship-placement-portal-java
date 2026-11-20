
package com.careerbridge.careerbridge_backend.service;

import com.careerbridge.careerbridge_backend.dto.CompanyRegistrationRequest;
import com.careerbridge.careerbridge_backend.dto.CompanyLoginRequest;
import com.careerbridge.careerbridge_backend.dto.CompanyResponse;
import com.careerbridge.careerbridge_backend.entity.Company;
import com.careerbridge.careerbridge_backend.repository.CompanyRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Transactional
    public CompanyResponse registerCompany(CompanyRegistrationRequest request) {

        if (companyRepository.existsById(request.getCompanyId())) {
            throw new RuntimeException("Company ID already exists");
        }

        if (companyRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        Company company = new Company();

        company.setCompanyId(request.getCompanyId());
        company.setCompanyName(request.getCompanyName());
        company.setEmail(request.getEmail());
        company.setPassword(request.getPassword());
        company.setContactNo(request.getContactNo());
        company.setLocation(request.getLocation());

        // New companies must wait for admin verification.
        company.setVerificationStatus("Pending");

        Company savedCompany = companyRepository.save(company);

        return convertToResponse(savedCompany);
    }

    public CompanyResponse loginCompany(CompanyLoginRequest request) {

        Company company = companyRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid email or password"));

        if (!company.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        if (!"Verified".equalsIgnoreCase(company.getVerificationStatus())) {
            throw new RuntimeException(
                    "Company is not verified by admin yet");
        }

        return convertToResponse(company);
    }

    public CompanyResponse getCompanyById(Integer companyId) {

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() ->
                        new RuntimeException("Company not found"));

        return convertToResponse(company);
    }

    @Transactional
    public CompanyResponse updateCompany(
            Integer companyId, CompanyRegistrationRequest request) {

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() ->
                        new RuntimeException("Company not found"));

        if (!company.getEmail().equalsIgnoreCase(request.getEmail())
                && companyRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        company.setCompanyName(request.getCompanyName());
        company.setEmail(request.getEmail());
        company.setContactNo(request.getContactNo());
        company.setLocation(request.getLocation());

        Company updatedCompany = companyRepository.save(company);

        return convertToResponse(updatedCompany);
    }

    private CompanyResponse convertToResponse(Company company) {

        CompanyResponse response = new CompanyResponse();

        response.setCompanyId(company.getCompanyId());
        response.setCompanyName(company.getCompanyName());
        response.setEmail(company.getEmail());
        response.setContactNo(company.getContactNo());
        response.setLocation(company.getLocation());
        response.setVerificationStatus(company.getVerificationStatus());

        return response;
    }
}

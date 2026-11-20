
package com.careerbridge.careerbridge_backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.careerbridge.careerbridge_backend.dto.AdminLoginRequest;
import com.careerbridge.careerbridge_backend.dto.AdminLoginResponse;
import com.careerbridge.careerbridge_backend.dto.CompanyResponse;
import com.careerbridge.careerbridge_backend.dto.StudentResponse;
import com.careerbridge.careerbridge_backend.dto.ApplicationResponse;

import com.careerbridge.careerbridge_backend.entity.Admin;
import com.careerbridge.careerbridge_backend.entity.Company;
import com.careerbridge.careerbridge_backend.entity.Student;
import com.careerbridge.careerbridge_backend.entity.Application;

import com.careerbridge.careerbridge_backend.repository.AdminRepository;
import com.careerbridge.careerbridge_backend.repository.CompanyRepository;
import com.careerbridge.careerbridge_backend.repository.StudentRepository;
import com.careerbridge.careerbridge_backend.repository.ApplicationRepository;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final CompanyRepository companyRepository;
    private final StudentRepository studentRepository;
    private final ApplicationRepository applicationRepository;

    public AdminService(AdminRepository adminRepository,
                        CompanyRepository companyRepository,
                        StudentRepository studentRepository,
                        ApplicationRepository applicationRepository) {

        this.adminRepository = adminRepository;
        this.companyRepository = companyRepository;
        this.studentRepository = studentRepository;
        this.applicationRepository = applicationRepository;
    }

    // Admin Login
    public AdminLoginResponse login(AdminLoginRequest request) {

        Admin admin = adminRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid admin email or password"));

        if (!admin.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid admin email or password");
        }

        return new AdminLoginResponse(
                admin.getAdminId(),
                admin.getName(),
                admin.getEmail(),
                admin.getDepartment(),
                "Admin login successful"
        );
    }

    // View All Companies
    public List<CompanyResponse> getAllCompanies() {

        return companyRepository.findAll()
                .stream()
                .map(this::convertToCompanyResponse)
                .collect(Collectors.toList());
    }

    // Verify Company
    public CompanyResponse verifyCompany(Integer companyId) {

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() ->
                        new RuntimeException("Company not found"));

        company.setVerificationStatus("Verified");

        return convertToCompanyResponse(companyRepository.save(company));
    }

    // Reject Company
    public CompanyResponse rejectCompany(Integer companyId) {

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() ->
                        new RuntimeException("Company not found"));

        company.setVerificationStatus("Rejected");

        return convertToCompanyResponse(companyRepository.save(company));
    }

    // Convert Company Entity into Response DTO
    private CompanyResponse convertToCompanyResponse(Company company) {

        CompanyResponse response = new CompanyResponse();

        response.setCompanyId(company.getCompanyId());
        response.setCompanyName(company.getCompanyName());
        response.setEmail(company.getEmail());
        response.setContactNo(company.getContactNo());
        response.setLocation(company.getLocation());
        response.setVerificationStatus(company.getVerificationStatus());

        return response;
    }

    // View All Students
    public List<StudentResponse> getAllStudents() {

        return studentRepository.findAll()
                .stream()
                .map(this::convertToStudentResponse)
                .collect(Collectors.toList());
    }

    // Delete Student
    public String deleteStudent(Integer studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));

        if (applicationRepository.existsByStudentId(studentId)) {
            throw new RuntimeException(
                    "Cannot delete student because application records exist"
            );
        }

        studentRepository.delete(student);

        return "Student deleted successfully";
    }

    // Convert Student Entity into Response DTO
    private StudentResponse convertToStudentResponse(Student student) {

        return new StudentResponse(
                student.getStudentId(),
                student.getName(),
                student.getEmail(),
                student.getCgpa(),
                student.getResumePath(),
                student.getBranchId()
        );
    }

    // View All Applications
    public List<ApplicationResponse> getAllApplications() {

        return applicationRepository.findAll()
                .stream()
                .map(this::convertToApplicationResponse)
                .collect(Collectors.toList());
    }

    // Update Application Status
   
public ApplicationResponse updateApplicationStatus(
        Integer applicationId, String status) {

    // Validate application status
    List<String> allowedStatuses = List.of(
            "Applied",
            "Shortlisted",
            "Rejected",
            "Selected"
    );

    if (status == null || !allowedStatuses.contains(status.trim())) {
        throw new RuntimeException(
                "Invalid status. Allowed values: Applied, Shortlisted, Rejected, Selected"
        );
    }

    Application application = applicationRepository.findById(applicationId)
            .orElseThrow(() ->
                    new RuntimeException("Application not found"));

    application.setStatus(status.trim());

    Application updatedApplication =
            applicationRepository.save(application);

    return convertToApplicationResponse(updatedApplication);
}


    // Convert Application Entity into Response DTO
    private ApplicationResponse convertToApplicationResponse(
            Application application) {

        ApplicationResponse response = new ApplicationResponse();

        response.setApplicationId(application.getApplicationId());
        response.setStudentId(application.getStudentId());
        response.setJobId(application.getJobId());
        response.setStatus(application.getStatus());
        response.setAppliedDate(application.getAppliedDate());

        return response;
    }
}

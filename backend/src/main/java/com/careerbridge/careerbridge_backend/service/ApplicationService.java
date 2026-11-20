
package com.careerbridge.careerbridge_backend.service;

import com.careerbridge.careerbridge_backend.dto.ApplicationRequest;
import com.careerbridge.careerbridge_backend.dto.ApplicationResponse;
import com.careerbridge.careerbridge_backend.entity.*;
import com.careerbridge.careerbridge_backend.repository.*;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final StudentRepository studentRepository;
    private final JobRepository jobRepository;
    private final StudentSkillRepository studentSkillRepository;
    private final JobSkillRepository jobSkillRepository;
    private final JobBranchRepository jobBranchRepository;

    public ApplicationService(
            ApplicationRepository applicationRepository,
            StudentRepository studentRepository,
            JobRepository jobRepository,
            StudentSkillRepository studentSkillRepository,
            JobSkillRepository jobSkillRepository,
            JobBranchRepository jobBranchRepository) {

        this.applicationRepository = applicationRepository;
        this.studentRepository = studentRepository;
        this.jobRepository = jobRepository;
        this.studentSkillRepository = studentSkillRepository;
        this.jobSkillRepository = jobSkillRepository;
        this.jobBranchRepository = jobBranchRepository;
    }

    public ApplicationResponse applyForJob(ApplicationRequest request) {

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new RuntimeException("Job not found"));

        if (applicationRepository.existsByStudentIdAndJobId(
                request.getStudentId(), request.getJobId())) {
            throw new RuntimeException("You have already applied for this job");
        }

        // Check minimum CGPA
        if (student.getCgpa() == null || job.getMinimumCgpa() == null
                || student.getCgpa().compareTo(job.getMinimumCgpa()) < 0) {
            throw new RuntimeException("You are not eligible due to CGPA requirements");
        }

        // Check branch eligibility
        List<JobBranch> eligibleBranches =
                jobBranchRepository.findByIdJobId(job.getJobId());

        if (!eligibleBranches.isEmpty()) {
            boolean branchEligible = eligibleBranches.stream()
                    .anyMatch(jb -> jb.getId().getBranchId()
                            .equals(student.getBranchId()));

            if (!branchEligible) {
                throw new RuntimeException("Your branch is not eligible for this job");
            }
        }

        // Check required skills
        List<JobSkill> requiredSkills =
                jobSkillRepository.findByIdJobId(job.getJobId());

        Set<Integer> studentSkills = studentSkillRepository
                .findByIdStudentId(student.getStudentId())
                .stream()
                .map(ss -> ss.getId().getSkillId())
                .collect(Collectors.toSet());

        for (JobSkill requiredSkill : requiredSkills) {
            if (!studentSkills.contains(requiredSkill.getId().getSkillId())) {
                throw new RuntimeException("You do not have all required skills");
            }
        }

        // Create application
        Application application = new Application();
        application.setStudentId(student.getStudentId());
        application.setJobId(job.getJobId());
        application.setStatus("Applied");
        application.setAppliedDate(LocalDate.now());

        return convertToResponse(applicationRepository.save(application));
    }

    public List<ApplicationResponse> getApplicationsByStudent(Integer studentId) {

        if (!studentRepository.existsById(studentId)) {
            throw new RuntimeException("Student not found");
        }

        return applicationRepository.findByStudentId(studentId)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public List<ApplicationResponse> getApplicationsByJob(Integer jobId) {

        if (!jobRepository.existsById(jobId)) {
            throw new RuntimeException("Job not found");
        }

        return applicationRepository.findByJobId(jobId)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private ApplicationResponse convertToResponse(Application application) {

        ApplicationResponse response = new ApplicationResponse();

        response.setApplicationId(application.getApplicationId());
        response.setStudentId(application.getStudentId());
        response.setJobId(application.getJobId());
        response.setStatus(application.getStatus());
        response.setAppliedDate(application.getAppliedDate());

        return response;
    }
}

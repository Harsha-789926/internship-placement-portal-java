
package com.careerbridge.careerbridge_backend.service;

import com.careerbridge.careerbridge_backend.dto.JobRequest;
import com.careerbridge.careerbridge_backend.dto.JobResponse;
import com.careerbridge.careerbridge_backend.entity.Job;
import com.careerbridge.careerbridge_backend.repository.JobRepository;
import com.careerbridge.careerbridge_backend.repository.CompanyRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;

    public JobService(
            JobRepository jobRepository,
            CompanyRepository companyRepository) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
    }

    // Create a new job
    @Transactional
    public JobResponse createJob(JobRequest request) {

        if (jobRepository.existsById(request.getJobId())) {
            throw new RuntimeException("Job ID already exists");
        }

        if (!companyRepository.existsById(request.getCompanyId())) {
            throw new RuntimeException("Company not found");
        }

        Job job = new Job();
        copyRequestToEntity(request, job);

        Job savedJob = jobRepository.save(job);

        return convertToResponse(savedJob);
    }

    // Get all jobs
    public List<JobResponse> getAllJobs() {

        return jobRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    // Get a job by ID
    public JobResponse getJobById(Integer jobId) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new RuntimeException("Job not found"));

        return convertToResponse(job);
    }

    // Get jobs posted by a particular company
    public List<JobResponse> getJobsByCompany(Integer companyId) {

        if (!companyRepository.existsById(companyId)) {
            throw new RuntimeException("Company not found");
        }

        return jobRepository.findByCompanyId(companyId)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    // Update a job
    @Transactional
    public JobResponse updateJob(Integer jobId, JobRequest request) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new RuntimeException("Job not found"));

        if (!companyRepository.existsById(request.getCompanyId())) {
            throw new RuntimeException("Company not found");
        }

        job.setCompanyId(request.getCompanyId());
        copyRequestToEntity(request, job);

        Job updatedJob = jobRepository.save(job);

        return convertToResponse(updatedJob);
    }

    // Delete a job
    @Transactional
    public void deleteJob(Integer jobId) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new RuntimeException("Job not found"));

        jobRepository.delete(job);
    }

    private void copyRequestToEntity(JobRequest request, Job job) {

        job.setJobId(request.getJobId());
        job.setCompanyId(request.getCompanyId());
        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setPackageAmount(request.getPackageAmount());
        job.setLocation(request.getLocation());
        job.setMinimumCgpa(request.getMinimumCgpa());
        job.setDeadline(request.getDeadline());
    }

    private JobResponse convertToResponse(Job job) {

        JobResponse response = new JobResponse();

        response.setJobId(job.getJobId());
        response.setCompanyId(job.getCompanyId());
        response.setTitle(job.getTitle());
        response.setDescription(job.getDescription());
        response.setPackageAmount(job.getPackageAmount());
        response.setLocation(job.getLocation());
        response.setMinimumCgpa(job.getMinimumCgpa());
        response.setDeadline(job.getDeadline());

        return response;
    }
}

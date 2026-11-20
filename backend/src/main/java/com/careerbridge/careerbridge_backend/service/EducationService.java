
package com.careerbridge.careerbridge_backend.service;

import com.careerbridge.careerbridge_backend.dto.EducationRequest;
import com.careerbridge.careerbridge_backend.dto.EducationResponse;
import com.careerbridge.careerbridge_backend.entity.Education;
import com.careerbridge.careerbridge_backend.repository.EducationRepository;
import com.careerbridge.careerbridge_backend.repository.StudentRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EducationService {

    private final EducationRepository educationRepository;
    private final StudentRepository studentRepository;

    public EducationService(
            EducationRepository educationRepository,
            StudentRepository studentRepository) {
        this.educationRepository = educationRepository;
        this.studentRepository = studentRepository;
    }

    public List<EducationResponse> getEducationByStudent(Integer studentId) {

        if (!studentRepository.existsById(studentId)) {
            throw new RuntimeException("Student not found with ID: " + studentId);
        }

        return educationRepository.findByStudentId(studentId)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    @Transactional
    public EducationResponse addEducation(EducationRequest request) {

        if (!studentRepository.existsById(request.getStudentId())) {
            throw new RuntimeException("Student not found");
        }

        Education education = new Education();

        education.setStudentId(request.getStudentId());
        education.setEducationLevel(request.getEducationLevel());
        education.setInstitution(request.getInstitution());
        education.setPercentage(request.getPercentage());
        education.setCgpa(request.getCgpa());
        education.setPassingYear(request.getPassingYear());

        Education savedEducation = educationRepository.save(education);

        return convertToResponse(savedEducation);
    }

    @Transactional
    public EducationResponse updateEducation(
            Integer educationId, EducationRequest request) {

        Education education = educationRepository.findById(educationId)
                .orElseThrow(() ->
                        new RuntimeException("Education record not found"));

        if (!studentRepository.existsById(request.getStudentId())) {
            throw new RuntimeException("Student not found");
        }

        education.setStudentId(request.getStudentId());
        education.setEducationLevel(request.getEducationLevel());
        education.setInstitution(request.getInstitution());
        education.setPercentage(request.getPercentage());
        education.setCgpa(request.getCgpa());
        education.setPassingYear(request.getPassingYear());

        Education updatedEducation = educationRepository.save(education);

        return convertToResponse(updatedEducation);
    }

    @Transactional
    public void deleteEducation(Integer educationId) {

        Education education = educationRepository.findById(educationId)
                .orElseThrow(() ->
                        new RuntimeException("Education record not found"));

        educationRepository.delete(education);
    }

    private EducationResponse convertToResponse(Education education) {

        EducationResponse response = new EducationResponse();

        response.setEducationId(education.getEducationId());
        response.setStudentId(education.getStudentId());
        response.setEducationLevel(education.getEducationLevel());
        response.setInstitution(education.getInstitution());
        response.setPercentage(education.getPercentage());
        response.setCgpa(education.getCgpa());
        response.setPassingYear(education.getPassingYear());

        return response;
    }
}

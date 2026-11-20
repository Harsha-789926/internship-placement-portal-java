
package com.careerbridge.careerbridge_backend.service;

import com.careerbridge.careerbridge_backend.dto.StudentSkillRequest;
import com.careerbridge.careerbridge_backend.dto.StudentSkillResponse;

import com.careerbridge.careerbridge_backend.entity.StudentSkill;
import com.careerbridge.careerbridge_backend.entity.StudentSkillId;
import com.careerbridge.careerbridge_backend.entity.Skill;

import com.careerbridge.careerbridge_backend.repository.StudentSkillRepository;
import com.careerbridge.careerbridge_backend.repository.SkillRepository;
import com.careerbridge.careerbridge_backend.repository.StudentRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentSkillService {

    private final StudentSkillRepository studentSkillRepository;
    private final SkillRepository skillRepository;
    private final StudentRepository studentRepository;

    public StudentSkillService(
            StudentSkillRepository studentSkillRepository,
            SkillRepository skillRepository,
            StudentRepository studentRepository) {

        this.studentSkillRepository = studentSkillRepository;
        this.skillRepository = skillRepository;
        this.studentRepository = studentRepository;
    }

    // View all available skills
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    // View skills belonging to a student
    public List<StudentSkillResponse> getStudentSkills(Integer studentId) {

        if (!studentRepository.existsById(studentId)) {
            throw new RuntimeException("Student not found");
        }

        return studentSkillRepository.findByIdStudentId(studentId)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    // Add a skill to a student's profile
    @Transactional
    public StudentSkillResponse addStudentSkill(StudentSkillRequest request) {

        Integer studentId = request.getStudentId();
        Integer skillId = request.getSkillId();

        if (!studentRepository.existsById(studentId)) {
            throw new RuntimeException("Student not found");
        }

        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        StudentSkillId id = new StudentSkillId(studentId, skillId);

        if (studentSkillRepository.existsById(id)) {
            throw new RuntimeException("Student already has this skill");
        }

        StudentSkill studentSkill = new StudentSkill(id);
        studentSkillRepository.save(studentSkill);

        StudentSkillResponse response = new StudentSkillResponse();
        response.setStudentId(studentId);
        response.setSkillId(skillId);
        response.setSkillName(skill.getSkillName());

        return response;
    }

    // Remove a skill from a student's profile
    @Transactional
    public void removeStudentSkill(Integer studentId, Integer skillId) {

        StudentSkillId id = new StudentSkillId(studentId, skillId);

        if (!studentRepository.existsById(studentId)) {
            throw new RuntimeException("Student not found");
        }

        if (!studentSkillRepository.existsById(id)) {
            throw new RuntimeException("Student skill record not found");
        }

        studentSkillRepository.deleteById(id);
    }

    // Convert entity into response DTO
    private StudentSkillResponse convertToResponse(StudentSkill studentSkill) {

        Integer skillId = studentSkill.getId().getSkillId();

        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        StudentSkillResponse response = new StudentSkillResponse();

        response.setStudentId(studentSkill.getId().getStudentId());
        response.setSkillId(skillId);
        response.setSkillName(skill.getSkillName());

        return response;
    }
}

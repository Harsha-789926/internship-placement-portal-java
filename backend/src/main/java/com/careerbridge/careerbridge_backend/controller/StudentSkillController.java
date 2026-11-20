
package com.careerbridge.careerbridge_backend.controller;

import com.careerbridge.careerbridge_backend.dto.StudentSkillRequest;
import com.careerbridge.careerbridge_backend.dto.StudentSkillResponse;
import com.careerbridge.careerbridge_backend.entity.Skill;
import com.careerbridge.careerbridge_backend.service.StudentSkillService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student-skills")
public class StudentSkillController {

    private final StudentSkillService studentSkillService;

    public StudentSkillController(StudentSkillService studentSkillService) {
        this.studentSkillService = studentSkillService;
    }

    // View all available skills
    @GetMapping("/available")
    public ResponseEntity<List<Skill>> getAllSkills() {
        return ResponseEntity.ok(studentSkillService.getAllSkills());
    }

    // View skills of a student
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<StudentSkillResponse>> getStudentSkills(
            @PathVariable Integer studentId) {

        return ResponseEntity.ok(
                studentSkillService.getStudentSkills(studentId));
    }

    // Add skill to student profile
    @PostMapping
    public ResponseEntity<StudentSkillResponse> addStudentSkill(
            @Valid @RequestBody StudentSkillRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentSkillService.addStudentSkill(request));
    }

    // Remove skill from student profile
    @DeleteMapping("/student/{studentId}/skill/{skillId}")
    public ResponseEntity<String> removeStudentSkill(
            @PathVariable Integer studentId,
            @PathVariable Integer skillId) {

        studentSkillService.removeStudentSkill(studentId, skillId);

        return ResponseEntity.ok("Student skill removed successfully");
    }
}

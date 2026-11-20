
package com.careerbridge.careerbridge_backend.controller;

import com.careerbridge.careerbridge_backend.dto.StudentUpdateRequest;
import com.careerbridge.careerbridge_backend.dto.StudentLoginRequest;
import com.careerbridge.careerbridge_backend.dto.StudentResponse;
import com.careerbridge.careerbridge_backend.dto.StudentRegistrationRequest;
import com.careerbridge.careerbridge_backend.service.StudentService;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/students")
@Validated

public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // GET: View all students
    @GetMapping
    public List<StudentResponse> getAllStudents() {
        return studentService.getAllStudents();
    }

    // GET: View student by ID
    @GetMapping("/{studentId}")
    public StudentResponse getStudentById(@PathVariable Integer studentId) {
        return studentService.getStudentById(studentId);
    }

    // POST: Register a new student
    @PostMapping("/register")
    public ResponseEntity<StudentResponse> registerStudent(
            @Valid @RequestBody StudentRegistrationRequest request) {

        StudentResponse response = studentService.registerStudent(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    // POST: Student login
    @PostMapping("/login")
    public ResponseEntity<StudentResponse> loginStudent(
            @Valid @RequestBody StudentLoginRequest request) {

        StudentResponse response = studentService.loginStudent(request);

        return ResponseEntity.ok(response);
    }

    // PUT: Update student profile
    @PutMapping("/{studentId}")
    public ResponseEntity<StudentResponse> updateStudentProfile(
            @PathVariable Integer studentId,
            @Valid @RequestBody StudentUpdateRequest request) {

        StudentResponse response =
                studentService.updateStudentProfile(studentId, request);

        return ResponseEntity.ok(response);
    }

    // DELETE: Delete student
    @DeleteMapping("/{studentId}")
    public ResponseEntity<String> deleteStudent(
            @PathVariable Integer studentId) {

        studentService.deleteStudent(studentId);

        return ResponseEntity.ok("Student deleted successfully");
    }



}

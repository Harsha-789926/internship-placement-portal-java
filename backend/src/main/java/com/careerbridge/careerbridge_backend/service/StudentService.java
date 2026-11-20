
package com.careerbridge.careerbridge_backend.service;


import com.careerbridge.careerbridge_backend.dto.StudentResponse;
import com.careerbridge.careerbridge_backend.dto.StudentUpdateRequest;
import com.careerbridge.careerbridge_backend.dto.StudentLoginRequest;
import com.careerbridge.careerbridge_backend.dto.StudentRegistrationRequest;
import com.careerbridge.careerbridge_backend.entity.Student;
import com.careerbridge.careerbridge_backend.repository.StudentRepository;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // View all students
    public List<StudentResponse> getAllStudents() {

        return studentRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    // View student by ID
    public StudentResponse getStudentById(Integer studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new RuntimeException("Student not found with ID: " + studentId));

        return convertToResponse(student);
    }

    // Register new student
    public StudentResponse registerStudent(StudentRegistrationRequest request) {

        if (studentRepository.existsById(request.getStudentId())) {
            throw new RuntimeException("Student ID already exists");
        }

        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        Student student = new Student();

        student.setStudentId(request.getStudentId());
        student.setName(request.getName());
        student.setEmail(request.getEmail());

        // Encrypt password before saving
        student.setPassword(passwordEncoder.encode(request.getPassword()));

        student.setCgpa(request.getCgpa());
        student.setBranchId(request.getBranchId());

        Student savedStudent = studentRepository.save(student);

        return convertToResponse(savedStudent);
    }

    // Student login
    public StudentResponse loginStudent(StudentLoginRequest request) {

        Student student = studentRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid email or password"));

        // Compare entered password with BCrypt hash
        if (!passwordEncoder.matches(
                request.getPassword(), student.getPassword())) {

            throw new RuntimeException("Invalid email or password");
        }

        return convertToResponse(student);
    }


    // Convert Student entity into StudentResponse
    private StudentResponse convertToResponse(Student student) {

        return new StudentResponse(
                student.getStudentId(),
                student.getName(),
                student.getEmail(),
                student.getCgpa(),
                student.getResumePath(),
                student.getBranchId()
        );
    }

    // Update student profile
    public StudentResponse updateStudentProfile(
            Integer studentId,
            StudentUpdateRequest request) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));

        if (studentRepository.existsByEmailAndStudentIdNot(
                request.getEmail(), studentId)) {

            throw new RuntimeException("Email already registered by another student");
        }

        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setCgpa(request.getCgpa());
        student.setBranchId(request.getBranchId());
        student.setResumePath(request.getResumePath());

        Student updatedStudent = studentRepository.save(student);

        return convertToResponse(updatedStudent);
    }

    // Delete student
    public void deleteStudent(Integer studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));

        studentRepository.delete(student);
    }


}

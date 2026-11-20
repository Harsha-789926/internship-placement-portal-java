package com.careerbridge.careerbridge_backend.repository;

import java.util.Optional;
import com.careerbridge.careerbridge_backend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    boolean existsByEmail(String email);
    Optional<Student> findByEmail(String email);
    boolean existsByEmailAndStudentIdNot(String email, Integer studentId);
}

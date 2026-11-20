
package com.careerbridge.careerbridge_backend.repository;

import com.careerbridge.careerbridge_backend.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EducationRepository extends JpaRepository<Education, Integer> {

    List<Education> findByStudentId(Integer studentId);
}

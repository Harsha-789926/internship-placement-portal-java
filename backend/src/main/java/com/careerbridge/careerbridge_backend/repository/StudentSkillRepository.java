
package com.careerbridge.careerbridge_backend.repository;

import com.careerbridge.careerbridge_backend.entity.StudentSkill;
import com.careerbridge.careerbridge_backend.entity.StudentSkillId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentSkillRepository
        extends JpaRepository<StudentSkill, StudentSkillId> {

    // Get all skills assigned to a particular student
    List<StudentSkill> findByIdStudentId(Integer studentId);

    // Check whether a student already has a particular skill
    boolean existsByIdStudentIdAndIdSkillId(
            Integer studentId,
            Integer skillId
    );
}

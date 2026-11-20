
package com.careerbridge.careerbridge_backend.repository;

import com.careerbridge.careerbridge_backend.entity.JobSkill;
import com.careerbridge.careerbridge_backend.entity.JobSkillId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobSkillRepository extends JpaRepository<JobSkill, JobSkillId> {

    List<JobSkill> findByIdJobId(Integer jobId);

}

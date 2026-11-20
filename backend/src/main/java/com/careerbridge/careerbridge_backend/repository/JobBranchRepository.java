
package com.careerbridge.careerbridge_backend.repository;

import com.careerbridge.careerbridge_backend.entity.JobBranch;
import com.careerbridge.careerbridge_backend.entity.JobBranchId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobBranchRepository extends JpaRepository<JobBranch, JobBranchId> {

    List<JobBranch> findByIdJobId(Integer jobId);
}

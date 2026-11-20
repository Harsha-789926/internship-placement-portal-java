
package com.careerbridge.careerbridge_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.careerbridge.careerbridge_backend.entity.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    List<Application> findByStudentId(Integer studentId);

    List<Application> findByJobId(Integer jobId);

    boolean existsByStudentIdAndJobId(Integer studentId, Integer jobId);

    boolean existsByStudentId(Integer studentId);
}

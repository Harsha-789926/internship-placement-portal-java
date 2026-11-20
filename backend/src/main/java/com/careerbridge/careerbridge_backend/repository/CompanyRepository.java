
package com.careerbridge.careerbridge_backend.repository;

import com.careerbridge.careerbridge_backend.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    Optional<Company> findByEmail(String email);

    boolean existsByEmail(String email);
}

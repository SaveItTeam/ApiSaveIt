package com.api.Repository;

import com.api.model.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
    Enterprise findByEmail(String email);
}

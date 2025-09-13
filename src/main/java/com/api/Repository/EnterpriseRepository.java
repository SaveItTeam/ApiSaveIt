package com.api.Repository;

import com.api.Model.Enterprise;
import com.api.dto.enterprise.EnterpriseResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
    Enterprise findByEmail(String email);
}

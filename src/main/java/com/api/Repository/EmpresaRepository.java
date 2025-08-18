package com.api.Repository;

import com.api.Model.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Enterprise, Long> {
}

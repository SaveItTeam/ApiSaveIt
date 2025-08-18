package com.api.Repository;

import com.api.Model.Lote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchRepository extends JpaRepository<Lote, Long> {
}

package com.api.Repository;

import com.api.Model.Produto;
import com.api.Model.Vitrine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VitrineRepository extends JpaRepository<Vitrine, Long> {
}

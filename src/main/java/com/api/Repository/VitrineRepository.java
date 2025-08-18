package com.api.Repository;

import com.api.Model.ShowCase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VitrineRepository extends JpaRepository<ShowCase, Long> {
}

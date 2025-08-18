package com.api.Repository;

import com.api.Model.Showcase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowcaseRepository extends JpaRepository<Showcase, Long> {
}

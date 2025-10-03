package com.api.Repository;

import com.api.Model.Showcase;
import com.api.dto.showcase.ShowcaseListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShowcaseRepository extends JpaRepository<Showcase, Long> {

    @Query("SELECT new com.api.dto.showcase.ShowcaseListDTO(s.id, p.name, i.image) FROM Showcase s " +
            "JOIN Batch b on b.id = s.batch_id " +
            "JOIN Product p on p.id = b.product_id " +
            "JOIN Image i on i.product_id = p.id")
    List<ShowcaseListDTO> findShowcaseWithProduct();
}

package com.api.repository;

import com.api.model.Product;
import com.api.model.Showcase;
import com.api.dto.showcase.ShowcaseListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowcaseRepository extends JpaRepository<Showcase, Long> {

    @Query("""
        SELECT new com.api.dto.showcase.ShowcaseListDTO(s.id, p.name, i.image)
        FROM Showcase s
        JOIN s.batch b
        JOIN b.product p
        JOIN Image i ON i.productId = p.id
        WHERE p.category = :category
    """)
    List<ShowcaseListDTO> findShowcaseWithProductByCategory(String category);

    @Query("""
        SELECT new com.api.dto.showcase.ShowcaseListDTO(s.id, p.name, i.image)
        FROM Showcase s
        JOIN s.batch b
        JOIN b.product p
        JOIN Image i ON i.productId = p.id
    """)
    List<ShowcaseListDTO> findShowcaseWithProduct();

    @Query("""
        SELECT new com.api.dto.showcase.ShowcaseListDTO(s.id, p.name, i.image)
        FROM Showcase s
        JOIN s.batch b
        JOIN b.product p
        JOIN Image i ON i.productId = p.id
        JOIN p.enterprise e
        WHERE e.id = :enterpriseId
    """)
    List<ShowcaseListDTO> findShowcaseWithProductByEnterpriseId(long enterpriseId);
    List<Showcase> findAllByEntranceDateAfter(LocalDateTime entranceDate);


}

package com.api.repository;

import com.api.model.Product;
import com.api.dto.product.ProductShowcaseStatusDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("""
        SELECT new com.api.dto.product.ProductShowcaseStatusDTO(
            e.id,
            e.name,
            p.id,
            p.name,
            CAST(b.expirationDate AS string),
            CASE WHEN s.id IS NOT NULL THEN 'SIM' ELSE 'NÃO' END,
            COALESCE(s.quantityShowcase, 0)
        )
        FROM Product p
        JOIN p.enterprise e
        JOIN Batch b ON b.product = p
        LEFT JOIN Showcase s ON s.batch = b
        WHERE e.id = :enterpriseId
        ORDER BY p.id
        """)
    List<ProductShowcaseStatusDTO> findProductsWithShowcaseStatusByEnterprise(@Param("enterpriseId") Long enterpriseId);
}

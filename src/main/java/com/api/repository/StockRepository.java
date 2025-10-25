package com.api.repository;

import com.api.dto.stock.StockByProductSummary;
import com.api.dto.stock.StockSummary;
import com.api.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    @Query(value = "SELECT * FROM moviments(:enterpriseId)", nativeQuery = true)
    List<StockSummary> getStockSummary(@Param("enterpriseId") Long enterpriseId);
    @Query(value = "SELECT * FROM moviments_by_product(:enterpriseId, :productId)", nativeQuery = true)
    List<StockByProductSummary> getStockSummaryByProduct(@Param("enterpriseId") Long enterpriseId, @Param("productId") Long productId);
}

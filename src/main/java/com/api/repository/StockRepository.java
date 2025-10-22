package com.api.repository;

import com.api.dto.stock.StockSummary;
import com.api.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    @Procedure(name = "moviments")
    List<StockSummary> getStockSummary(@Param("enterprise_id") Long enterpriseId);
}

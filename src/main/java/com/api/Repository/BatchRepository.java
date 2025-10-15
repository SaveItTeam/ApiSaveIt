package com.api.Repository;

import com.api.Model.Batch;
import com.api.dto.Batch.BatchListDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BatchRepository extends JpaRepository<Batch, Long> {

    @Query("select new com.api.dto.Batch.BatchListDTO(p.id, p.name, b.expirationDate , b.quantityMeasure, i.image, b.unitMeasure) " +
            "from Batch b " +
            "join Product p on b.productId = p.id " +
            "join Image i on p.id = i.productId " +
            "join Enterprise e on p.enterpriseId = e.id " +
            "where e.id = :enterpriseId")
    List<BatchListDTO> listOfBatches(@Param("enterpriseId") long enterpriseId);
}

package com.api.Repository;

import com.api.model.Batch;
import com.api.dto.batch.BatchListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BatchRepository extends JpaRepository<Batch, Long> {
    @Query("select new com.api.dto.batch.BatchListDTO(p.id, p.name, b.expirationDate ,CONCAT(b.quantity, ' ', b.unitMeasure) , i.image) " +
            "from Batch b " +
            "join Product p on b.productId = p.id " +
            "join Image i on p.id = i.productId " +
            "join Enterprise e on p.enterpriseId = e.id " +
            "where e.id = :enterpriseId")
    List<BatchListDTO> listOfBatches(@Param("enterpriseId") long enterpriseId);
}

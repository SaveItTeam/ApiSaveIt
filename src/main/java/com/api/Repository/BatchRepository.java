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

    @Query(value = "select p.id, p.name, b.expiration_date, b.quantity, i.image from batch b\n" +
            "join product p on b.product_id = p.id\n" +
            "join image i on p.id = i.product_id\n" +
            "join enterprise e on p.enterprise_id = e.id\n" +
            "where e.id = :enterpriseId", nativeQuery = true)
    List<BatchListDTO> listOfBatches(@Param("enterpriseId") long enterpriseId);
}

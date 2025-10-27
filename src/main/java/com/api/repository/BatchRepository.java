package com.api.repository;

import com.api.model.Batch;
import com.api.dto.batch.BatchListDTO;
import com.api.dto.batch.BatchInsertRequestDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface BatchRepository extends JpaRepository<Batch, Long> {
    @Query(value = "SELECT * FROM get_batches_by_enterprise(:enterpriseId)", nativeQuery = true)
    List<BatchListDTO> listOfBatches(@Param("enterpriseId") Long enterpriseId);

    Batch findByBatchCode(String batchCode);

    @Procedure(procedureName = "public.add_full_product")
    void batchInsert(@Param("p_name") String pName, @Param("p_description") String pDescription,
                     @Param("p_category") String pCategory, @Param("p_brand") String pBrand,
                     @Param("p_enterprise_id") Long pEnterpriseId, @Param("p_image") String pImage,
                     @Param("p_quantity") Integer pQuantity, @Param("p_entry_date") Date pEntryDate,
                     @Param("p_expiration_date") Date pExpirationDate, @Param("p_batch_code") String pBatchCode,
                     @Param("p_unit_measure") String pUnitMeasure);
}

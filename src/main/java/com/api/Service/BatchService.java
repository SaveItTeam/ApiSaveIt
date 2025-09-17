package com.api.Service;

import com.api.Model.Batch;
import com.api.Model.Enterprise;
import com.api.Model.Product;
import com.api.Repository.BatchRepository;
import com.api.dto.Batch.BatchListDTO;
import com.api.dto.Batch.BatchRequestDTO;
import com.api.dto.Batch.BatchResponseDTO;
import com.api.dto.enterprise.EnterpriseResponseDTO;
import com.api.dto.product.ProductRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class BatchService {
    private final BatchRepository batchRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public BatchService(BatchRepository batchRepository, ObjectMapper objectMapper) {
        this.batchRepository = batchRepository;
        this.objectMapper = objectMapper;
    }


    public List<BatchResponseDTO> listBatch(){
        List<Batch> batchs = batchRepository.findAll();
        List<BatchResponseDTO> returnList = new ArrayList<>();
        for(Batch batch: batchs){
            returnList.add(objectMapper.convertValue(batch, BatchResponseDTO.class));
        }
        return returnList;
    }

    public List<BatchListDTO> listProductBatch(long enterpriseId){
        return batchRepository.listOfBatches(enterpriseId);
    }

    public void insertBatch(BatchRequestDTO batch) {
        Batch response = objectMapper.convertValue(batch, Batch.class);
        batchRepository.save(response);
    }

    public void deleteBatch(Long id) {
        batchRepository.deleteById(id);
    }

    public BatchResponseDTO updateBatch(Long id, BatchRequestDTO batchAtualizado) {
        Batch batch = batchRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Lote com ID " + id + " não encontrado"));

        batch.setUnit_measure(batchAtualizado.getUnit_measure());
        batch.setEntry_date(batchAtualizado.getEntry_date());
        batch.setBatch_code(batchAtualizado.getBatch_code());
        batch.setExpiration_date(batchAtualizado.getExpiration_date());
        batch.setQuantity(batchAtualizado.getQuantity());
        batch.setProduct_id(batchAtualizado.getProduct_id());

        batchRepository.save(batch);
        return objectMapper.convertValue(batch, BatchResponseDTO.class);
    }

    public BatchResponseDTO updateBatchPartial(Long id, Map<String, Object> updates) {
        Batch batch = batchRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Lote com ID " + id + " não encontrado"));

        if (updates.containsKey("unit_measure")) {
            batch.setUnit_measure((String) updates.get("unit_measure"));
        }
        if (updates.containsKey("entry_date")) {
            batch.setEntry_date((Date) updates.get("entry_date"));
        }
        if (updates.containsKey("batch_code")) {
            batch.setBatch_code((String) updates.get("batch_code"));
        }
        if (updates.containsKey("expiration_date")) {
            batch.setExpiration_date((Date) updates.get("expiration_date"));
        }
        if (updates.containsKey("quantity")) {
            batch.setQuantity((int) updates.get("quantity"));
        }
        if (updates.containsKey("product_id")) {
            batch.setProduct_id((long) updates.get("product_id"));
        }

        batchRepository.save(batch);
        return objectMapper.convertValue(batch, BatchResponseDTO.class);
    }
}

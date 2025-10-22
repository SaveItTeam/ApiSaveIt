package com.api.service;

import com.api.Exception.InvalidCategoryException;
import com.api.Exception.InvalidExpirationDateException;
import com.api.Exception.InvalidUnitMeasureException;
import com.api.model.Batch;
import com.api.repository.BatchRepository;
import com.api.dto.batch.BatchListDTO;
import com.api.dto.batch.BatchRequestDTO;
import com.api.dto.batch.BatchResponseDTO;
import com.api.dto.batch.BatchInsertRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public void insertBatchEntity(BatchInsertRequestDTO batch) {
        if (batch == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requisição de lote ausente");
        }

        var product = batch.getProduct();
        var image = batch.getImage();
        var lote = batch.getBatch();

        if (!(lote.getUnitMeasure().equals("KG"))&&(lote.getUnitMeasure().equals("L"))){
            throw new InvalidUnitMeasureException("Unidade de medida inválida para o produto cadastrado");
        }

        if (
                !product.getCategory().equals("Laticínios") &&
                        !product.getCategory().equals("Embutidos") &&
                        !product.getCategory().equals("Outros")
        ) {
            throw new InvalidCategoryException("Categoria inválida para o produto cadastrado");
        }

        if (lote.getEntryDate().before(lote.getEntryDate())) {
            throw new InvalidExpirationDateException("Data de validade não pode ser menor ou igual a data de entrada");
        }

        try {
            batchRepository.batchInsert(
                    product.getName(),
                    product.getDescription(),
                    product.getCategory(),
                    product.getBrand(),
                    product.getEnterpriseId(),
                    image.getImage(),
                    lote.getQuantity(),
                    lote.getEntryDate(),
                    lote.getExpirationDate(),
                    lote.getBatchCode(),
                    lote.getUnitMeasure()
            );
        }catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Violação de integridade de dados: " + ex.getMessage());
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao acessar o banco de dados: " + ex.getMessage());
        }
    }

    public BatchResponseDTO getBatchById(Long id) {
        Batch batch = batchRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Lote com ID " + id + " não encontrado"));
        return objectMapper.convertValue(batch, BatchResponseDTO.class);
    }

    public void deleteBatch(Long id) {
        batchRepository.deleteById(id);
    }

    public BatchResponseDTO updateBatch(Long id, BatchRequestDTO batchAtualizado) {
        Batch batch = batchRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Lote com ID " + id + " não encontrado"));

        batch.setUnitMeasure(batchAtualizado.getUnitMeasure());
        batch.setEntryDate(batchAtualizado.getEntryDate());
        batch.setBatchCode(batchAtualizado.getBatchCode());
        batch.setExpirationDate(batchAtualizado.getExpirationDate());
        batch.setQuantity(batchAtualizado.getQuantity());
        batch.setProductId(batchAtualizado.getProductId());

        batchRepository.save(batch);
        return objectMapper.convertValue(batch, BatchResponseDTO.class);
    }

    public BatchResponseDTO updateBatchPartial(Long id, Map<String, Object> updates) {
        Batch batch = batchRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Lote com ID " + id + " não encontrado"));

        if (updates.containsKey("unitMeasure")) {
            batch.setUnitMeasure((String) updates.get("unitMeasure"));
        }
        if (updates.containsKey("entryDate")) {
            batch.setEntryDate((Date) updates.get("entryDate"));
        }
        if (updates.containsKey("batchCode")) {
            batch.setBatchCode((String) updates.get("batchCode"));
        }
        if (updates.containsKey("expirationDate")) {
            batch.setExpirationDate((Date) updates.get("expirationDate"));
        }
        if (updates.containsKey("quantity")) {
            batch.setQuantity((Integer) updates.get("quantity"));
        }
        if (updates.containsKey("productId")) {
            batch.setProductId((long) updates.get("productId"));
        }
        if (updates.containsKey("maxQuantity")){
            batch.setMaxQuantity((Integer) updates.get("maxQuantity"));
        }

        batchRepository.save(batch);
        return objectMapper.convertValue(batch, BatchResponseDTO.class);
    }
}

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
import com.api.repository.ProductRepository;
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
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    public BatchService(BatchRepository batchRepository) {
        this.batchRepository = batchRepository;
    }

    private BatchResponseDTO mapToDTO(Batch batch) {
        Long productId = batch.getProduct() != null ? batch.getProduct().getId() : null;
        return new BatchResponseDTO(
                batch.getId(),
                batch.getUnitMeasure(),
                batch.getEntryDate(),
                batch.getBatchCode(),
                batch.getExpirationDate(),
                batch.getMaxQuantity(),
                batch.getQuantity(),
                productId
        );
    }

    public List<BatchResponseDTO> listBatch() {
        List<Batch> batches = batchRepository.findAll();
        List<BatchResponseDTO> returnList = new ArrayList<>();
        for (Batch batch : batches) {
            returnList.add(mapToDTO(batch));
        }
        return returnList;
    }

    public List<BatchListDTO> listProductBatch(long enterpriseId) {
        return batchRepository.listOfBatches(enterpriseId);
    }

    public void insertBatch(BatchRequestDTO batchDTO) {
        Batch batch = new Batch();
        batch.setUnitMeasure(batchDTO.getUnitMeasure());
        batch.setEntryDate(batchDTO.getEntryDate());
        batch.setBatchCode(batchDTO.getBatchCode());
        batch.setExpirationDate(batchDTO.getExpirationDate());
        batch.setMaxQuantity(batchDTO.getMaxQuantity());
        batch.setQuantity(batchDTO.getQuantity());

        if (batchDTO.getProductId() != null) {
            var product = productRepository.findById(batchDTO.getProductId())
                    .orElseThrow(() -> new NoSuchElementException("Produto com ID " + batchDTO.getProductId() + " não encontrado"));
            batch.setProduct(product);
        }

        batchRepository.save(batch);
    }

    public void insertBatchEntity(BatchInsertRequestDTO batch) {
        if (batch == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requisição de lote ausente");
        }

        var product = batch.getProduct();
        var image = batch.getImage();
        var lote = batch.getBatch();

        if (!(lote.getUnitMeasure().equals("KG") || lote.getUnitMeasure().equals("L"))) {
            throw new InvalidUnitMeasureException("Unidade de medida inválida para o produto cadastrado");
        }

        if (!product.getCategory().equals("Laticínios") &&
                !product.getCategory().equals("Embutidos") &&
                !product.getCategory().equals("Outros")) {
            throw new InvalidCategoryException("Categoria inválida para o produto cadastrado");
        }

        if (lote.getEntryDate().after(lote.getExpirationDate())) {
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
                    new java.sql.Date(lote.getEntryDate().getTime()),
                    new java.sql.Date(lote.getExpirationDate().getTime()),
                    lote.getBatchCode(),
                    lote.getUnitMeasure()
            );
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("Erro de integridade ao inserir a empresa: " + ex.getMessage());
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao acessar o banco de dados: " + ex.getMessage());
        }
    }

    public BatchResponseDTO getBatchById(Long id) {
        Batch batch = batchRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Lote com ID " + id + " não encontrado"));
        return mapToDTO(batch);
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
        batch.setMaxQuantity(batchAtualizado.getMaxQuantity());

        if (batchAtualizado.getProductId() != null) {
            var product = productRepository.findById(batchAtualizado.getProductId())
                    .orElseThrow(() -> new NoSuchElementException("Produto com ID " + batchAtualizado.getProductId() + " não encontrado"));
            batch.setProduct(product);
        }

        batchRepository.save(batch);
        return mapToDTO(batch);
    }

    public BatchResponseDTO updateBatchPartial(Long id, Map<String, Object> updates) {
        Batch batch = batchRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Lote com ID " + id + " não encontrado"));

        if (updates.containsKey("unitMeasure")) batch.setUnitMeasure((String) updates.get("unitMeasure"));
        if (updates.containsKey("entryDate")) batch.setEntryDate((Date) updates.get("entryDate"));
        if (updates.containsKey("batchCode")) batch.setBatchCode((String) updates.get("batchCode"));
        if (updates.containsKey("expirationDate")) batch.setExpirationDate((Date) updates.get("expirationDate"));
        if (updates.containsKey("quantity")) batch.setQuantity(((Number) updates.get("quantity")).intValue());
        if (updates.containsKey("maxQuantity")) batch.setMaxQuantity(((Number) updates.get("maxQuantity")).intValue());
        if (updates.containsKey("productId")) {
            Long productId = ((Number) updates.get("productId")).longValue();
            var product = productRepository.findById(productId)
                    .orElseThrow(() -> new NoSuchElementException("Produto com ID " + productId + " não encontrado"));
            batch.setProduct(product);
        }

        batchRepository.save(batch);
        return mapToDTO(batch);
    }
}

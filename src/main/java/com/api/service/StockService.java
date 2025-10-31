package com.api.service;

import java.time.LocalDateTime;
import java.util.*;

import com.api.dto.stock.*;
import com.api.exception.InvalidQuantityException;
import com.api.model.Batch;
import com.api.repository.BatchRepository;
import com.api.repository.EnterpriseRepository;
import com.api.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.model.Stock;
import com.api.repository.StockRepository;
import com.api.dto.stock.StockRequestDTO;
import com.api.dto.stock.StockResponseDTO;

@Service
public class StockService {

    private final StockRepository stockRepository;
    private final BatchRepository batchRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final ProductRepository productRepository;

    @Autowired
    public StockService(StockRepository stockRepository, BatchRepository batchRepository, EnterpriseRepository enterpriseRepository, ProductRepository productRepository){
        this.stockRepository = stockRepository;
        this.batchRepository = batchRepository;
        this.enterpriseRepository = enterpriseRepository;
        this.productRepository = productRepository;
    }

    public List<StockResponseDTO> listStock() {
        List<Stock> stocks = stockRepository.findAll();
        List<StockResponseDTO> stockResponseDTOS = new ArrayList<>();
        for (Stock stock : stocks) {
            stockResponseDTOS.add(toStockResponseDTO(stock));
        }
        return stockResponseDTOS;
    }

    public void insertStock(StockRequestDTO stockDTO) {
        Optional<Batch> batchOpt = batchRepository.findById(stockDTO.getBatchId());
        if (batchOpt.isEmpty()) throw new EntityNotFoundException("Lote não encontrado.");

        Batch batch = batchOpt.get();

        if (batch.getQuantity() < stockDTO.getQuantityOutput()) {
            throw new InvalidQuantityException(Map.of("quantityInput", "A quantidade de entrada não pode ser maior que a quantidade disponível no lote."));
        }

        batch.setQuantity((batch.getQuantity() - stockDTO.getQuantityOutput()) + stockDTO.getQuantityInput());

        if (stockDTO.getDiscardQuantity() != null && stockDTO.getDiscardQuantity() > 0) {
            if (batch.getQuantity() < stockDTO.getDiscardQuantity()) {
                throw new InvalidQuantityException(Map.of("discardQuantity", "A quantidade de descarte não pode ser maior que a quantidade disponível no lote."));
            }
            batch.setQuantity(batch.getQuantity() - stockDTO.getDiscardQuantity());
        }

        batchRepository.save(batch);

        Stock stock = toStockEntity(stockDTO);
        stockRepository.save(stock);
    }

    public void deleteStock(Long id) {
        stockRepository.deleteById(id);
    }

    public List<StockMoviments> getStockMoviments(Long enterpriseId) {
        try {
            return  stockRepository.getStockMovimentsByProduct(enterpriseId);
        }catch(Exception e) {
            throw new RuntimeException("Erro ao buscar resumo do estoque: " +e.getMessage(), e);
        }
    }

    public List<StockQuantity> getStockQuantities(Long enterpriseId) {
        try {
            return stockRepository.getStockQuantities(enterpriseId);
        }catch(Exception e) {
            throw new RuntimeException("Erro ao buscar a quantidade de cada produto: "+e.getMessage(), e);
        }
    }
    public List<StockSummary> getStockSummary(Long enterpriseId) {
        try {
            return stockRepository.getStockSummary(enterpriseId);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar resumo do estoque: " + e.getMessage(), e);
        }
    }

    public List<StockByProductSummary> getStockSummaryByProduct(Long enterpriseId, Long productId) {
        if (enterpriseRepository.findById(enterpriseId).isEmpty()) {
            throw new EntityNotFoundException("Empresa não encontrada.");
        }
        if (productRepository.findById(productId).isEmpty()) {
            throw new EntityNotFoundException("Produto não encontrado.");
        }
        try {
            return stockRepository.getStockSummaryByProduct(enterpriseId, productId);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar resumo do estoque por produto: " + e.getMessage(), e);
        }
    }

    public StockResponseDTO updateStock(Long id, StockRequestDTO stockAtualizado) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Estoque com ID " + id + " não encontrado"));

        applyStockRequestDTO(stock, stockAtualizado);
        stockRepository.save(stock);
        return toStockResponseDTO(stock);
    }

    public StockResponseDTO updateStockPartial(Long id, Map<String, Object> updates) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Estoque com ID " + id + " não encontrado"));

        if (updates.containsKey("quantityInput")) {
            stock.setQuantityInput((Integer) updates.get("quantityInput"));
        }
        if (updates.containsKey("quantityOutput")) {
            stock.setQuantityOutput((Integer) updates.get("quantityOutput"));
        }
        if (updates.containsKey("batchId")) {
            stock.setBatchId(Long.parseLong(updates.get("batchId").toString()));
        }
        if (updates.containsKey("createdAt")) {
            stock.setCreatedAt(LocalDateTime.parse(updates.get("createdAt").toString()));
        }
        if (updates.containsKey("discardQuantity")) {
            stock.setDiscardQuantity((Integer) updates.get("discardQuantity"));
        }
        if (updates.containsKey("discardReason")) {
            stock.setDiscardReason(updates.get("discardReason").toString());
        }
        if (updates.containsKey("productId")) {
            stock.setProductId(Long.parseLong(updates.get("productId").toString()));
        }

        stockRepository.save(stock);
        return toStockResponseDTO(stock);
    }

    private StockResponseDTO toStockResponseDTO(Stock stock) {
        return new StockResponseDTO(
                stock.getId(),
                stock.getQuantityInput(),
                stock.getQuantityOutput(),
                stock.getBatchId(),
                stock.getProductId(),
                stock.getDiscardQuantity(),
                stock.getDiscardReason(),
                stock.getCreatedAt()
        );
    }

    private Stock toStockEntity(StockRequestDTO dto) {
        Stock stock = new Stock();
        applyStockRequestDTO(stock, dto);
        return stock;
    }

    private void applyStockRequestDTO(Stock stock, StockRequestDTO dto) {
        stock.setQuantityInput(dto.getQuantityInput());
        stock.setQuantityOutput(dto.getQuantityOutput());
        stock.setBatchId(dto.getBatchId());
        stock.setProductId(dto.getProductId());
        stock.setDiscardQuantity(dto.getDiscardQuantity());
        stock.setDiscardReason(dto.getDiscardReason());
        stock.setCreatedAt(dto.getCreatedAt());
    }

}

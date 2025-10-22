package com.api.service;

import java.time.LocalDateTime;
import java.util.*;

import com.api.exception.InvalidQuantityException;
import com.api.model.Batch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.model.Stock;
import com.api.repository.StockRepository;
import com.api.dto.stock.StockRequestDTO;
import com.api.dto.stock.StockResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class StockService {

    private final StockRepository stockRepository;
    private final ObjectMapper objectMapper;
    private final com.api.repository.BatchRepository batchRepository;

    @Autowired
    public StockService(StockRepository stockRepository, ObjectMapper objectMapper, com.api.repository.BatchRepository batchRepository){
        this.stockRepository = stockRepository;
        this.objectMapper = objectMapper;
        this.batchRepository = batchRepository;
    }

    //    Métodos de busca
    public List<StockResponseDTO> listStock(){
        List<Stock> stocks = stockRepository.findAll();
        List<StockResponseDTO> stockResponseDTOS = new ArrayList<>();
        for (Stock stock : stocks) {
            stockResponseDTOS.add(objectMapper.convertValue(stock, StockResponseDTO.class));
        }
        return stockResponseDTOS;
    }

    public void insertStock(StockRequestDTO stock) {
        Optional<Batch> batch = batchRepository.findById(stock.getBatchId());

        if (batch.get().getQuantity() < stock.getQuantityOutput()) {
            throw new InvalidQuantityException(Map.of("quantityInput", "A quantidade de entrada não pode ser maior que a quantidade disponível no lote."));
        }else {
            batch.get().setQuantity( (batch.get().getQuantity() - stock.getQuantityOutput()) + stock.getQuantityInput() );
            if (stock.getDiscardQuantity() > 0) {
                if (batch.get().getQuantity() < stock.getDiscardQuantity()) {
                    throw new InvalidQuantityException(Map.of("discardQuantity", "A quantidade de descarte não pode ser maior que a quantidade disponível no lote."));
                } else {
                    batch.get().setQuantity(batch.get().getQuantity() - stock.getDiscardQuantity());
                    batchRepository.save(batch.get());
                }
            }else {
                batchRepository.save(batch.get());
            }
        }

        Stock stockRequest = objectMapper.convertValue(stock, Stock.class);
        stockRepository.save(stockRequest);
    }

    public void deleteStock(Long id) {
        stockRepository.deleteById(id);
    }




    public StockResponseDTO updateStock(Long id, StockRequestDTO stockAtualizado) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Estoque com ID " + id + " não encontrado"));

        stock.setQuantityInput(stockAtualizado.getQuantityInput());
        stock.setQuantityOutput(stockAtualizado.getQuantityOutput());
        stock.setBatchId(stockAtualizado.getBatchId());
        stock.setCreatedAt(stockAtualizado.getCreatedAt());
        stock.setDiscardQuantity(stockAtualizado.getDiscardQuantity());
        stock.setDiscardReason(stockAtualizado.getDiscardReason());
        stock.setProductId(stockAtualizado.getProductId());


        stockRepository.save(stock);
        return objectMapper.convertValue(stock, StockResponseDTO.class);
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
            stock.setBatchId(Long.valueOf(updates.get("batchId").toString()));
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
            stock.setProductId(Long.valueOf(updates.get("productId").toString()));
        }

        stockRepository.save(stock);
        return objectMapper.convertValue(stock, StockResponseDTO.class);
    }

    private void validarQuantidade(StockRequestDTO stock) {
        Map<String, String> erros = new HashMap<>();

        if (stock.getQuantityInput() <= 0) {
            erros.put("quantity", "A quantidade deve ser maior que zero.");
        }

        if (stock.getQuantityOutput() < 0) {
            erros.put("quantity_output", "A quantidade de saída não pode ser negativa.");
        }

        if (!erros.isEmpty()) {
            throw new InvalidQuantityException(erros);
        }
    }

}

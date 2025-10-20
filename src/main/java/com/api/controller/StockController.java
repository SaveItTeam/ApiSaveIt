package com.api.controller;

import com.api.exception.GlobalException;
import com.api.openapi.StockOpenApi;
import com.api.Service.StockService;
import com.api.dto.stock.StockRequestDTO;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import com.api.dto.stock.StockResponseDTO;



@RestController
@RequestMapping("/api/stock")
public class StockController implements StockOpenApi {

    private final StockService stockService;
    private GlobalException ge;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }


    @GetMapping("/selecionar")
    public ResponseEntity<List<StockResponseDTO>> listStock() {
        // Se lançar uma RuntimeException aqui, o Spring vai chamar o método do GlobalException automaticamente
        List<StockResponseDTO> stocks = stockService.listStock();
        return ResponseEntity.ok(stocks);
    }

    @PostMapping("/inserir")
    public ResponseEntity<?> inserirVitrine(@RequestBody StockRequestDTO stock) {
        stockService.insertStock(stock);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Estoque inserido com sucesso!");
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> deleteStock(@PathVariable Long id) {
        stockService.deleteStock(id);
        return ResponseEntity.ok("Estoque excluído com sucesso!");
    }



    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> updateStock(@PathVariable Long id, @Valid @RequestBody StockRequestDTO stockAtualizado) {
        stockService.updateStock(id, stockAtualizado);
        return ResponseEntity.ok("Estoque atualizado com sucesso!");
    }



    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<?> updateStockPartial(@PathVariable Long id, @Valid @RequestBody Map<String, Object> updates) {
        StockResponseDTO stockResponseDTO = stockService.updateStockPartial(id, updates);
        return ResponseEntity.ok("Estoque atualizado parcialmente com sucesso!");
    }

}

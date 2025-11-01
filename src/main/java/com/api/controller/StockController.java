package com.api.controller;

import com.api.dto.stock.*;
import com.api.exception.GlobalException;
import com.api.openapi.StockOpenApi;
import com.api.service.StockService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


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
        List<StockResponseDTO> stocks = stockService.listStock();
        return ResponseEntity.ok(stocks);
    }

    @GetMapping("/relatorioProduto/{enterpriseId}")
    public ResponseEntity<List<StockSummary>> listSummary(@PathVariable Long enterpriseId) {
        List<StockSummary> response = stockService.getStockSummary(enterpriseId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/listarMovimentacoesEstoque/{enterpriseId}")
    public ResponseEntity<List<StockMoviments>> listStockMovimentsByProduct(@PathVariable Long enterpriseId) {
        List<StockMoviments> response = stockService.getStockMoviments(enterpriseId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/listarQuantidadesProduto/{enterpriseId}")
    public ResponseEntity<List<StockQuantity>> listProductQuantities(@PathVariable Long enterpriseId) {
        List<StockQuantity> stockQuantityList = stockService.getStockQuantities(enterpriseId);
        return ResponseEntity.ok(stockQuantityList);
    }

    @GetMapping("/relatorioProdutoPorProduto/{enterpriseId}/{productId}")
    public ResponseEntity<List<StockByProductSummary>> listSummaryByProductId(@PathVariable Long enterpriseId, @PathVariable Long productId) {
        List<StockByProductSummary> response = stockService.getStockSummaryByProduct(enterpriseId, productId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/inserir")
    public ResponseEntity<?> inserirStock(@RequestBody StockRequestDTO stock) {

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

package com.api.Controller;

import com.api.Exception.GlobalException;
import com.api.Model.Showcase;
import com.api.Model.Stock;
import com.api.Service.ShowcaseService;
import com.api.Service.StockService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.Socket;
import java.util.List;
import java.util.Map;

public class StockController {

    private final StockService stockService;
    private GlobalException ge;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }


    @GetMapping("/selecionar")
    public ResponseEntity<List<Stock>> listStock() {
        // Se lançar uma RuntimeException aqui, o Spring vai chamar o método do GlobalException automaticamente
        List<Stock> stocks = stockService.listStock();
        return ResponseEntity.ok(stocks);
    }



    @PostMapping("/inserir")
    public ResponseEntity<?> inserirVitrine(@RequestBody Stock stock) {
        Stock stockSalvo = stockService.insertStock(stock);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Estoque inserido com sucesso! ID: " + stockSalvo.getId());
    }



    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> deleteStock(@PathVariable Long id) {
        stockService.deleteStock(id);
        return ResponseEntity.ok("Estoque excluído com sucesso!");
    }



    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> updateStock(@PathVariable Long id, @Valid @RequestBody Stock stockAtualizado) {
        stockService.updateStock(id, stockAtualizado);
        return ResponseEntity.ok("Estoque atualizado com sucesso!");
    }



    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<?> updateStockPartial(@PathVariable Long id, @Valid @RequestBody Map<String, Object> updates) {
        stockService.updateStockPartial(id, updates);
        return ResponseEntity.ok("Estoque atualizado parcialmente com sucesso!");
    }

}

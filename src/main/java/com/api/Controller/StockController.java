package com.api.Controller;

import com.api.Exception.GlobalException;
import com.api.Model.Showcase;
import com.api.Model.Stock;
import com.api.Service.ShowcaseService;
import com.api.Service.StockService;
import com.api.dto.estock.StockRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.Socket;
import java.util.List;
import java.util.Map;

import com.api.dto.estock.StockResponseDTO;



@RestController
@RequestMapping("/api/stock")
public class StockController {

    private final StockService stockService;
    private GlobalException ge;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }


    @GetMapping("/selecionar")
    @Operation(summary = "Listar todos os estoques")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de estoques retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<List<StockResponseDTO>> listStock() {
        // Se lançar uma RuntimeException aqui, o Spring vai chamar o método do GlobalException automaticamente
        List<StockResponseDTO> stocks = stockService.listStock();
        return ResponseEntity.ok(stocks);
    }



    @PostMapping("/inserir")
    @Operation(summary = "Inserir um novo estoque")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Estoque inserido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<?> inserirVitrine(@RequestBody StockRequestDTO stock) {
        stockService.insertStock(stock);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Estoque inserido com sucesso!");
    }



    @DeleteMapping("/excluir/{id}")
    @Operation(summary = "Excluir um estoque")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estoque excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Estoque não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<?> deleteStock(@PathVariable Long id) {
        stockService.deleteStock(id);
        return ResponseEntity.ok("Estoque excluído com sucesso!");
    }



    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar um estoque")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estoque atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Estoque não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<?> updateStock(@PathVariable Long id, @Valid @RequestBody StockRequestDTO stockAtualizado) {
        stockService.updateStock(id, stockAtualizado);
        return ResponseEntity.ok("Estoque atualizado com sucesso!");
    }



    @PatchMapping("/atualizarParcial/{id}")
    @Operation(summary = "Atualizar parcialmente um estoque")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estoque atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Estoque não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<?> updateStockPartial(@PathVariable Long id, @Valid @RequestBody Map<String, Object> updates) {
        StockResponseDTO stockResponseDTO = stockService.updateStockPartial(id, updates);
        return ResponseEntity.ok("Estoque atualizado parcialmente com sucesso!" + stockResponseDTO);
    }

}

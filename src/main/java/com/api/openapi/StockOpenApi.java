package com.api.openapi;

import com.api.dto.stock.StockRequestDTO;
import com.api.dto.stock.StockResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

public interface StockOpenApi {

    @Operation(summary = "Listar todos os estoques")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de estoques retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<List<StockResponseDTO>> listStock();

    @Operation(summary = "Inserir um novo estoque")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Estoque inserido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> inserirStock(@RequestBody StockRequestDTO stock);

    @Operation(summary = "Excluir um estoque")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estoque excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Estoque não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<?> deleteStock(@PathVariable Long id);

    @Operation(summary = "Atualizar um estoque")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estoque atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Estoque não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> updateStock(Long id, StockRequestDTO stockAtualizado);


    @Operation(summary = "Atualizar parcialmente um estoque")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estoque atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Estoque não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> updateStockPartial(Long id, Map<String, Object> updates);
}

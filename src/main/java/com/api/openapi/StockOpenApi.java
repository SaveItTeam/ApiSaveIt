package com.api.openapi;

import com.api.dto.stock.StockRequestDTO;
import com.api.dto.stock.StockResponseDTO;
import com.api.dto.stock.StockByProductSummary;
import com.api.dto.stock.StockSummary;
import com.api.dto.stock.StockQuantity;
import com.api.dto.stock.StockMoviments;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Operation(
            summary = "Listar movimentações de estoque",
            description = "Retorna todos os registros de movimentação de estoque da empresa informada"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movimentações retornadas com sucesso",
                    content = @Content(schema = @Schema(implementation = StockMoviments.class))),
            @ApiResponse(responseCode = "404", description = "Empresa não encontrada")
    })
    ResponseEntity<List<StockMoviments>> listStockMovimentsByProduct(
            @Parameter(description = "ID da empresa", required = true)
            @PathVariable Long enterpriseId
    );

    @Operation(
            summary = "Resumo de estoque por empresa",
            description = "Retorna um relatório resumido do estoque de todos os produtos de uma empresa"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resumo retornado com sucesso",
                    content = @Content(schema = @Schema(implementation = StockSummary.class))),
            @ApiResponse(responseCode = "404", description = "Empresa não encontrada")
    })
    ResponseEntity<List<StockSummary>> listSummary(
            @Parameter(description = "ID da empresa", required = true)
            @PathVariable Long enterpriseId
    );

    @Operation(
            summary = "Listar quantidade de produtos no estoque",
            description = "Retorna a quantidade atual de cada produto da empresa"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quantidades retornadas com sucesso",
                    content = @Content(schema = @Schema(implementation = StockQuantity.class))),
            @ApiResponse(responseCode = "404", description = "Empresa não encontrada")
    })
    ResponseEntity<List<StockQuantity>> listProductQuantities(
            @Parameter(description = "ID da empresa", required = true)
            @PathVariable Long enterpriseId
    );


    @Operation(
            summary = "Resumo de movimentações por produto",
            description = "Retorna um relatório de movimentações de estoque para um produto específico"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resumo retornado com sucesso",
                    content = @Content(schema = @Schema(implementation = StockByProductSummary.class))),
            @ApiResponse(responseCode = "404", description = "Dados não encontrados")
    })
    ResponseEntity<List<StockByProductSummary>> listSummaryByProductId(
            @Parameter(description = "ID da empresa", required = true)
            @PathVariable Long enterpriseId,

            @Parameter(description = "ID do produto", required = true)
            @PathVariable Long productId
    );

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

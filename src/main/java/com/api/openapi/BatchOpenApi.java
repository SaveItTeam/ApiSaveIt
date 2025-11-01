package com.api.openapi;

import com.api.dto.batch.BatchInsertRequestDTO;
import com.api.dto.batch.BatchListDTO;
import com.api.dto.batch.BatchRequestDTO;
import com.api.dto.batch.BatchResponseDTO;
import com.api.dto.product.ProductResponseInfoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

public interface BatchOpenApi {

    @Operation(summary = "Listar todos os lotes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de lotes retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<List<BatchResponseDTO>> listBatch();

    @Operation(summary = "Buscar lote por SKU")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lote retornado com sucesso",
                    content = @Content(schema = @Schema(implementation = BatchResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Lote não encontrado")
    })
    ResponseEntity<BatchResponseDTO> findBySKU(
            @Parameter(description = "SKU do produto", required = true) @PathVariable String sku
    );

    @Operation(summary = "Inserir um novo lote")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lote inserido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> insertBatch(@Valid BatchInsertRequestDTO batch);

    @Operation(summary = "Listar produtos com seus lotes por ID da empresa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de produtos com lotes retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<List<BatchListDTO>> listProdutosLote(long enterpriseId);

    @Operation(summary = "Obter informações de um produto pelo ID do lote")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Informações do produto retornadas com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<ProductResponseInfoDTO> getProductInfo(Long id);

    @Operation(summary = "Excluir um lote pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lote excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Lote não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> deleteBatch(Long id);

    @Operation(summary = "Atualizar um lote pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lote atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Lote não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> updateBatch(Long id,@Valid BatchRequestDTO batchAtualizado);

    @Operation(summary = "Atualizar parcialmente um lote pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lote atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Lote não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> updateBatchPartial(Long id,Map<String, Object> updates);
}

package com.api.openapi;

import com.api.dto.product.ProductRequestDTO;
import com.api.dto.product.ProductResponseDTO;
import com.api.dto.product.ProductShowcaseStatusDTO;
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

public interface ProductOpenApi {

    @GetMapping("/selecionar")
    @Operation(summary = "Listar todos os produtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<List<ProductResponseDTO>> listProduct();

    @Operation(summary = "Listar produtos com status de vitrine por empresa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos retornados com sucesso",
                    content = @Content(schema = @Schema(implementation = ProductShowcaseStatusDTO.class))),
            @ApiResponse(responseCode = "404", description = "Empresa não encontrada")
    })
    ResponseEntity<List<ProductShowcaseStatusDTO>> listProductsWithShowcaseStatusByEnterprise(
            @Parameter(description = "ID da empresa", required = true) @PathVariable Long enterpriseId
    );

    @PostMapping("/inserir")
    @Operation(summary = "Inserir um novo produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto inserido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> insertProduct(ProductRequestDTO product);


    @DeleteMapping("/excluir/{id}")
    @Operation(summary = "Excluir um produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> deleteProduct(Long id);

    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar um produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> updateProduct(Long id, ProductRequestDTO productAtualizado);

    @PatchMapping("/atualizarParcial/{id}")
    @Operation(summary = "Atualizar parcialmente um produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> updateProductPartial(Long id, Map<String, Object> updates);

}

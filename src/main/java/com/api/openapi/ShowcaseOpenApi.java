package com.api.openapi;

import com.api.dto.showcase.ShowcaseListDTO;
import com.api.dto.showcase.ShowcaseRequestDTO;
import com.api.dto.showcase.ShowcaseResponseDTO;
import com.api.dto.product.ProductShowcaseStatusDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface ShowcaseOpenApi {

    @Operation(summary = "Listar todas as vitrines")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de vitrines retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<List<ShowcaseResponseDTO>> listShowcase();

    @Operation(summary = "Listar vitrines por ID da empresa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de vitrines retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhuma vitrine encontrada para o ID da empresa"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<List<ShowcaseListDTO>> listShowcaseByEnterpriseId(long enterpriseId);


    @Operation(summary = "Listar novos produtos ou vitrines desde o último check")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos retornados com sucesso",
                    content = @Content(schema = @Schema(implementation = ShowcaseResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Formato de data inválido")
    })
    ResponseEntity<?> getProdutosNovos(
            @Parameter(description = "Data do último check no formato yyyy-MM-dd'T'HH:mm:ss")
            @RequestParam(required = false) String ultimoCheck
    );


    @Operation(summary = "Inserir uma nova vitrine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vitrine inserida com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> insertShowcase(@Valid ShowcaseRequestDTO showCase);


    @Operation(summary = "Excluir uma vitrine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vitrine excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Vitrine não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> deleteShowcase(Long id);


    @Operation(summary = "Atualizar uma vitrine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vitrine atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Vitrine não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> updateShowcase(Long id,@Valid ShowcaseRequestDTO showcaseAtualizado);


    @Operation(summary = "Listar vitrine com imagens e nomes dos produtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de vitrine retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<List<ShowcaseListDTO>> listShowcasaWithProduct(String category);

    @Operation(summary = "Atualizar parcialmente uma vitrine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vitrine atualizado parcialmente com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Vitrine não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> updateShowcasePartial(Long id,Map<String, Object> updates);



    }

package com.api.openapi;

import com.api.dto.showcase.ShowcaseListDTO;
import com.api.dto.showcase.ShowcaseRequestDTO;
import com.api.dto.showcase.ShowcaseResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

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


    @Operation(summary = "Inserir uma nova vitrine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vitrine inserida com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> insertShowcase(ShowcaseRequestDTO showCase);


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
    ResponseEntity<?> updateShowcase(Long id, ShowcaseRequestDTO showcaseAtualizado);


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

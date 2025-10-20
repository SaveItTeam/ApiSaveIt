package com.api.openapi;

import com.api.model.Enterprise;
import com.api.dto.enterprise.EnterpriseInsertDTO;
import com.api.dto.enterprise.EnterpriseResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface EnterpriseOpenApi {

    @Operation(summary = "Listar todas as empresas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de empresas retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<List<EnterpriseResponseDTO>> listEnterprise();

    @Operation(summary = "Inserir uma nova empresa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Empresa inserida com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> insertEnterprise(EnterpriseInsertDTO enterpriseInsert);


    @Operation(summary = "Listar empresa por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empresa retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Empresa não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<EnterpriseResponseDTO> listEnterpriseById(Long id);


    @Operation(summary = "Listar empresa por email")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empresa retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Empresa não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<EnterpriseResponseDTO> listEnterpriseByEmail(String email);


    @Operation(summary = "Excluir uma empresa")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empresa excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Empresa não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> deleteEnterprise(Long id);


    @Operation(summary = "Atualizar uma empresa")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empresa atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Empresa não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> updateEnterprise(Long id, Enterprise enterpriseAtualizado);


    @Operation(summary = "Atualizar parcialmente uma empresa")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empresa atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Empresa não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> updateEnterprisePartial(Long id, Map<String, Object> updates);
}

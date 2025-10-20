package com.api.openapi;

import com.api.dto.plan.PlanRequestDTO;
import com.api.dto.plan.PlanResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface PlanOpenApi {
    @GetMapping("/selecionar")
    @Operation(summary = "Listar todos os planos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de planos retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<List<PlanResponseDTO>> listPlan();

    @PostMapping("/inserir")
    @Operation(summary = "Inserir um novo plano")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Plano inserido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> insertPlan(@RequestBody PlanRequestDTO plan);
    @DeleteMapping("/excluir/{id}")
    @Operation(summary = "Excluir um plano")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plano excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Plano não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> deletePlan(@PathVariable Long id);
    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar um plano")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plano atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Plano não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> updatePlan(@PathVariable Long id, @Valid @RequestBody PlanRequestDTO planAtualizado);


}
package com.api.openapi;

import com.api.dto.admin.AdminRequestDTO;
import com.api.dto.admin.AdminResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface AdminOpenApi {

    @Operation(summary = "Listar todos os admins")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de admins retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<List<AdminResponseDTO>> listarAdmin();

    @Operation(summary = "Inserir um novo admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Admin inserido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> inserirAdmin(AdminRequestDTO admin);
    @Operation(summary = "Excluir um admin pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Admin excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Admin não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> excluirAdmin(Long id);
    @Operation(summary = "Atualizar um admin pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Admin atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Admin não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> updateAdmin(Long id, AdminRequestDTO adminAtualizado);
    @Operation(summary = "Atualizar parcialmente um admin pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Admin atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Admin não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> atualizarAdminParcial(Long id, Map<String, Object> updates);
}

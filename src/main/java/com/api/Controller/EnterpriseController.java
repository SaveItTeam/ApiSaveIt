package com.api.Controller;

import com.api.Exception.GlobalException;
import com.api.Model.Address;
import com.api.Model.Enterprise;
import com.api.Service.AddressService;
import com.api.Service.EnterpriseService;
import com.api.dto.address.AddressRequestDTO;
import com.api.dto.address.AddressResponseDTO;
import com.api.dto.enterprise.EnterpriseInsertDTO;
import com.api.dto.enterprise.EnterpriseRequestDTO;
import com.api.dto.enterprise.EnterpriseResponseDTO;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/enterprise")
public class EnterpriseController {


    private final EnterpriseService enterpriseService;
    private final AddressService addressService;
    private GlobalException ge;

    @Autowired
    public EnterpriseController(EnterpriseService enterpriseService, AddressService addressService) {
        this.enterpriseService = enterpriseService;
        this.addressService = addressService;
    }

    @GetMapping("/selecionar")
    @Operation(summary = "Listar todas as empresas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de empresas retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<List<EnterpriseResponseDTO>> listEnterprise() {
        List<EnterpriseResponseDTO> enterprises = enterpriseService.listEnterprise();
        return ResponseEntity.ok(enterprises);
    }

    @PostMapping("/inserir")
    @Operation(summary = "Inserir uma nova empresa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Empresa inserida com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<?> insertEnterprise(@RequestBody EnterpriseInsertDTO enterpriseInsert) {
        AddressResponseDTO addressResponseDTO = addressService.insertAddress(enterpriseInsert.getAddress());

        EnterpriseRequestDTO enterpriseResponseDTO = enterpriseInsert.getEnterprise();
        enterpriseResponseDTO.setEndereco_id(addressResponseDTO.getId());
        enterpriseService.insertEnterprise(enterpriseResponseDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Empresa inserida com sucesso!");
    }


    @GetMapping("listarEmail/{email}")
    @Operation(summary = "Listar empresa por email")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empresa retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Empresa não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<EnterpriseResponseDTO> listEnterpriseByEmail(@PathVariable String email) {
        EnterpriseResponseDTO responseDTO = enterpriseService.findByEmail(email);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/excluir/{id}")
    @Operation(summary = "Excluir uma empresa")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empresa excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Empresa não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<?> deleteEnterprise(@PathVariable Long id) {
        enterpriseService.deleteEnterprise(id);
        return ResponseEntity.ok("Empresa excluído com sucesso!");
    }


    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar uma empresa")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empresa atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Empresa não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<?> updateEnterprise(@PathVariable Long id, @Valid @RequestBody Enterprise enterpriseAtualizado) {
        enterpriseService.updateEnterprise(id, enterpriseAtualizado);
        return ResponseEntity.ok("Empresa atualizado com sucesso!");
    }

    @PatchMapping("/atualizarParcial/{id}")
    @Operation(summary = "Atualizar parcialmente uma empresa")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empresa atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Empresa não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<?> updateEnterprisePartial(@PathVariable Long id, @Valid @RequestBody Map<String, Object> updates) {
        enterpriseService.updateEnterprisePartial(id, updates);
        return ResponseEntity.ok("Empresa atualizado parcialmente com sucesso!");
    }

}

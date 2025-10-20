package com.api.controller;

import com.api.exception.GlobalException;
import com.api.Model.Enterprise;
import com.api.OpenAPI.EnterpriseOpenApi;
import com.api.Service.AddressService;
import com.api.Service.EnterpriseService;
import com.api.dto.address.AddressResponseDTO;
import com.api.dto.enterprise.EnterpriseInsertDTO;
import com.api.dto.enterprise.EnterpriseRequestDTO;
import com.api.dto.enterprise.EnterpriseResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/enterprise")
public class EnterpriseController implements EnterpriseOpenApi {
    private final EnterpriseService enterpriseService;
    private final AddressService addressService;
    private GlobalException ge;

    @Autowired
    public EnterpriseController(EnterpriseService enterpriseService, AddressService addressService) {
        this.enterpriseService = enterpriseService;
        this.addressService = addressService;
    }

    @GetMapping("/selecionar")
    public ResponseEntity<List<EnterpriseResponseDTO>> listEnterprise() {
        List<EnterpriseResponseDTO> enterprises = enterpriseService.listEnterprise();
        return ResponseEntity.ok(enterprises);
    }

    @PostMapping("/inserir")
    public ResponseEntity<?> insertEnterprise(@RequestBody EnterpriseInsertDTO enterpriseInsert) {
        AddressResponseDTO addressResponseDTO = addressService.insertAddress(enterpriseInsert.getAddress());

        EnterpriseRequestDTO enterpriseResponseDTO = enterpriseInsert.getEnterprise();
        enterpriseResponseDTO.setAddressId(addressResponseDTO.getId());
        enterpriseService.insertEnterprise(enterpriseResponseDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Empresa inserida com sucesso!");
    }

    @GetMapping("listarId/{id}")
    public ResponseEntity<EnterpriseResponseDTO> listEnterpriseById(@PathVariable Long id) {
        EnterpriseResponseDTO responseDTO = enterpriseService.findById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("listarEmail/{email}")
    public ResponseEntity<EnterpriseResponseDTO> listEnterpriseByEmail(@PathVariable String email) {
        EnterpriseResponseDTO responseDTO = enterpriseService.findByEmail(email);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> deleteEnterprise(@PathVariable Long id) {
        enterpriseService.deleteEnterprise(id);
        return ResponseEntity.ok("Empresa excluído com sucesso!");
    }


    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> updateEnterprise(@PathVariable Long id, @Valid @RequestBody Enterprise enterpriseAtualizado) {
        enterpriseService.updateEnterprise(id, enterpriseAtualizado);
        return ResponseEntity.ok("Empresa atualizado com sucesso!");
    }

    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<?> updateEnterprisePartial(@PathVariable Long id, @Valid @RequestBody Map<String, Object> updates) {
        enterpriseService.updateEnterprisePartial(id, updates);
        return ResponseEntity.ok("Empresa atualizado parcialmente com sucesso!");
    }

}

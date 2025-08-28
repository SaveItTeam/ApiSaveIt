package com.api.Controller;

import com.api.Exception.GlobalException;
import com.api.Model.Enterprise;
import com.api.Service.EnterpriseService;
import com.api.dto.enterprise.EnterpriseResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/empresa")
public class EnterpriseController {


    private final EnterpriseService enterpriseService;
    private GlobalException ge;

    @Autowired
    public EnterpriseController(EnterpriseService enterpriseService) {
        this.enterpriseService = enterpriseService;
    }

    @GetMapping("/selecionar")
    public ResponseEntity<List<EnterpriseResponseDTO>> listEnterprise() {
        List<EnterpriseResponseDTO> enterprises = enterpriseService.listEnterprise();
        return ResponseEntity.ok(enterprises);
    }

    @PostMapping("/inserir")
    public ResponseEntity<?> insertEnterprise(@RequestBody Enterprise enterprise) {
        enterpriseService.insertEnterprise(enterprise);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Empresa inserido com sucesso!");
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

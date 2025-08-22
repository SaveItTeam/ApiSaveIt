package com.api.Controller;

import com.api.Exception.GlobalException;
import com.api.Model.Enterprise;
import com.api.Service.EnterpriseService;
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
    public ResponseEntity<List<Enterprise>> listarEmpresas() {
        // Se lançar uma RuntimeException aqui, o Spring vai chamar o método do GlobalException automaticamente
        List<Enterprise> enterprises = enterpriseService.listarEmpresa();
        return ResponseEntity.ok(enterprises);
    }

    @PostMapping("/inserir")
    public ResponseEntity<?> inserirEmpresa(@RequestBody Enterprise enterprise) {
        Enterprise enterpriseSalvo = enterpriseService.inserirEmpresa(enterprise);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Empresa inserido com sucesso! ID: " + enterpriseSalvo.getId());
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluirEmpresa(@PathVariable Long id) {
        enterpriseService.excluirEmpresa(id);
        return ResponseEntity.ok("Empresa excluído com sucesso!");
    }


    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarEmpresa(@PathVariable Long id, @Valid @RequestBody Enterprise enterpriseAtualizado) {
        enterpriseService.atualizarEmpresa(id, enterpriseAtualizado);
        return ResponseEntity.ok("Empresa atualizado com sucesso!");
    }

    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<?> atualizarEnderecoParcial(@PathVariable Long id, @Valid @RequestBody Map<String, Object> updates) {
        enterpriseService.atualizarEmpresaParcial(id, updates);
        return ResponseEntity.ok("Empresa atualizado parcialmente com sucesso!");
    }

}

package com.api.Controller;

import com.api.Exception.GlobalException;
import com.api.Model.Empresa;
import com.api.Model.Endereco;
import com.api.Service.EmpresaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/endereco")
public class EmpresaController {


    private final EmpresaService empresaService;
    private GlobalException ge;

    @Autowired
    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping("/selecionar")
    public ResponseEntity<List<Empresa>> listarEmpresas() {
        // Se lançar uma RuntimeException aqui, o Spring vai chamar o método do GlobalException automaticamente
        List<Empresa> empresas = empresaService.listarEmpresa();
        return ResponseEntity.ok(empresas);
    }

    @PostMapping("/inserir")
    public ResponseEntity<?> inserirEmpresa(@RequestBody Empresa empresa) {
        Empresa empresaSalvo = empresaService.inserirEmpresa(empresa);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Empresa inserido com sucesso! ID: " + empresaSalvo.getId());
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluirEmpresa(@PathVariable Long id) {
        empresaService.excluirEmpresa(id);
        return ResponseEntity.ok("Empresa excluído com sucesso!");
    }


    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarEmpresa(@PathVariable Long id, @Valid @RequestBody Empresa empresaAtualizado) {
        empresaService.atualizarEmpresa(id, empresaAtualizado);
        return ResponseEntity.ok("Empresa atualizado com sucesso!");
    }

    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<?> atualizarEnderecoParcial(@PathVariable Long id, @Valid @RequestBody Map<String, Object> updates) {
        empresaService.atualizarEmpresaParcial(id, updates);
        return ResponseEntity.ok("Empresa atualizado parcialmente com sucesso!");
    }

}

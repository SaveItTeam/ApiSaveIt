package com.api.Controller;

import com.api.Exception.GlobalException;
import com.api.Model.Endereco;
import com.api.Service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/endereco")
public class EnderecoController {

    private final EnderecoService enderecoService;
    private GlobalException ge;

    @Autowired
    public EnderecoController(EnderecoService enderecoService) {
            this.enderecoService = enderecoService;
    }


    @GetMapping("/selecionar")
    public ResponseEntity<List<Endereco>> listarEnderecos() {
        // Se lançar uma RuntimeException aqui, o Spring vai chamar o método do GlobalException automaticamente
        List<Endereco> enderecos = enderecoService.listarEnderecos();
        return ResponseEntity.ok(enderecos);
    }



    @PostMapping("/inserir")
    public ResponseEntity<?> inserirEndereco(@RequestParam Endereco endereco) {
        Endereco enderecoSalvo = enderecoService.inserirEndereco(endereco);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Endereco inserido com sucesso! ID: " + enderecoSalvo.getId());
    }



    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluirEndereco(@PathVariable Long id) {
        enderecoService.excluirEndereco(id);
        return ResponseEntity.ok("Endereco excluído com sucesso!");
    }



    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarEndereco(@PathVariable Long id, @Valid @RequestParam Endereco enderecoAtualizado) {
        enderecoService.atualizarEndereco(id, enderecoAtualizado);
        return ResponseEntity.ok("Endereco atualizado com sucesso!");
    }



    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<?> atualizarEnderecoParcial(@PathVariable Long id, @Valid @RequestParam Map<String, Object> updates) {
        enderecoService.atualizarEnderecoParcial(id, updates);
        return ResponseEntity.ok("Endereco atualizado parcialmente com sucesso!");
    }



}
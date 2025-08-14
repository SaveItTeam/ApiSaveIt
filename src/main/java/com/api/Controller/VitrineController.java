package com.api.Controller;

import com.api.Exception.GlobalException;
import com.api.Model.Produto;
import com.api.Model.Vitrine;
import com.api.Service.ProdutoService;
import com.api.Service.VitrineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vitrine")
public class VitrineController {
    private final VitrineService vitrineService;
    private GlobalException ge;

    @Autowired
    public VitrineController(VitrineService vitrineService) {
        this.vitrineService = vitrineService;
    }


    @GetMapping("/selecionar")
    public ResponseEntity<List<Vitrine>> listarVitrine() {
        // Se lançar uma RuntimeException aqui, o Spring vai chamar o método do GlobalException automaticamente
        List<Vitrine> vitrines = vitrineService.listarVitrine();
        return ResponseEntity.ok(vitrines);
    }



    @PostMapping("/inserir")
    public ResponseEntity<?> inserirVitrine(@RequestBody Vitrine vitrine) {
        Vitrine vitrineSalvo = vitrineService.inserirVitrine(vitrine);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Vitrine inserido com sucesso! ID: " + vitrineSalvo.getId());
    }



    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluirVitrine(@PathVariable Long id) {
        vitrineService.excluirVitrine(id);
        return ResponseEntity.ok("Vitrine excluído com sucesso!");
    }



    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarVitrine(@PathVariable Long id, @Valid @RequestBody Vitrine vitrineAtualizado) {
        vitrineService.atualizarVitrine(id, vitrineAtualizado);
        return ResponseEntity.ok("Vitrine atualizado com sucesso!");
    }



    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<?> atualizarVitrineParcial(@PathVariable Long id, @Valid @RequestBody Map<String, Object> updates) {
        vitrineService.atualizarVitrineParcial(id, updates);
        return ResponseEntity.ok("Vitrine atualizado parcialmente com sucesso!");
    }


}

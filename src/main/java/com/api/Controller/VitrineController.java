package com.api.Controller;

import com.api.Exception.GlobalException;
import com.api.Model.ShowCase;
import com.api.Service.ShowCaseService;
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
    private final ShowCaseService showCaseService;
    private GlobalException ge;

    @Autowired
    public VitrineController(ShowCaseService showCaseService) {
        this.showCaseService = showCaseService;
    }


    @GetMapping("/selecionar")
    public ResponseEntity<List<ShowCase>> listarVitrine() {
        // Se lançar uma RuntimeException aqui, o Spring vai chamar o método do GlobalException automaticamente
        List<ShowCase> showCases = showCaseService.listarVitrine();
        return ResponseEntity.ok(showCases);
    }



    @PostMapping("/inserir")
    public ResponseEntity<?> inserirVitrine(@RequestBody ShowCase showCase) {
        ShowCase showCaseSalvo = showCaseService.inserirVitrine(showCase);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Vitrine inserido com sucesso! ID: " + showCaseSalvo.getId());
    }



    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluirVitrine(@PathVariable Long id) {
        showCaseService.excluirVitrine(id);
        return ResponseEntity.ok("Vitrine excluído com sucesso!");
    }



    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarVitrine(@PathVariable Long id, @Valid @RequestBody ShowCase showCaseAtualizado) {
        showCaseService.atualizarVitrine(id, showCaseAtualizado);
        return ResponseEntity.ok("Vitrine atualizado com sucesso!");
    }



    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<?> atualizarVitrineParcial(@PathVariable Long id, @Valid @RequestBody Map<String, Object> updates) {
        showCaseService.atualizarVitrineParcial(id, updates);
        return ResponseEntity.ok("Vitrine atualizado parcialmente com sucesso!");
    }


}

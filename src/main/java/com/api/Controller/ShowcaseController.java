package com.api.Controller;

import com.api.Exception.GlobalException;
import com.api.Model.Showcase;
import com.api.Service.ShowcaseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vitrine")
public class ShowcaseController {
    private final ShowcaseService showCaseService;
    private GlobalException ge;

    @Autowired
    public ShowcaseController(ShowcaseService showCaseService) {
        this.showCaseService = showCaseService;
    }


    @GetMapping("/selecionar")
    public ResponseEntity<List<Showcase>> listShowcase() {
        List<Showcase> showcases = showCaseService.listShowcase();
        return ResponseEntity.ok(showcases);
    }



    @PostMapping("/inserir")
    public ResponseEntity<?> insertShowcase(@RequestBody Showcase showCase) {
        Showcase showcaseSalvo = showCaseService.insertShowcase(showCase);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Vitrine inserido com sucesso! ID: " + showcaseSalvo.getId());
    }



    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> deleteShowcase(@PathVariable Long id) {
        showCaseService.deleteShowcase(id);
        return ResponseEntity.ok("Vitrine excluído com sucesso!");
    }



    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> updateShowcase(@PathVariable Long id, @Valid @RequestBody Showcase showcaseAtualizado) {
        showCaseService.updateShowcase(id, showcaseAtualizado);
        return ResponseEntity.ok("Vitrine atualizado com sucesso!");
    }



    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<?> updateShowcasePartial(@PathVariable Long id, @Valid @RequestBody Map<String, Object> updates) {
        showCaseService.updateShowcasePartial(id, updates);
        return ResponseEntity.ok("Vitrine atualizado parcialmente com sucesso!");
    }


}

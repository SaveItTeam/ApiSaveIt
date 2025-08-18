package com.api.Controller;

import com.api.Exception.GlobalException;
import com.api.Model.Lote;
import com.api.Service.LoteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/lote")
public class LoteController {
    private final LoteService loteService;
    private GlobalException ge;

    @Autowired
    public LoteController(LoteService loteService) {
        this.loteService = loteService;
    }


    @GetMapping("/selecionar")
    public ResponseEntity<List<Lote>> listarLotes() {
        // Se lançar uma RuntimeException aqui, o Spring vai chamar o método do GlobalException automaticamente
        List<Lote> lotes = loteService.listarLotes();
        return ResponseEntity.ok(lotes);
    }



    @PostMapping("/inserir")
    public ResponseEntity<?> inserirLote(@RequestBody Lote lote) {
        Lote loteSalvo = loteService.inserirLote(lote);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Lote inserido com sucesso! ID: " + loteSalvo.getId());
    }



    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluirLote(@PathVariable Long id) {
        loteService.excluirLote(id);
        return ResponseEntity.ok("Lote excluído com sucesso!");
    }



    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarLote(@PathVariable Long id, @Valid @RequestBody Lote loteAtualizado) {
        loteService.atualizarLote(id, loteAtualizado);
        return ResponseEntity.ok("Lote atualizado com sucesso!");
    }



    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<?> atualizarLoteParcial(@PathVariable Long id, @Valid @RequestBody Map<String, Object> updates) {
        loteService.atualizarLoteParcial(id, updates);
        return ResponseEntity.ok("Lote atualizado parcialmente com sucesso!");
    }


}

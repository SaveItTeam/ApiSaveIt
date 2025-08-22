package com.api.Controller;

import com.api.Exception.GlobalException;
import com.api.Model.Batch;
import com.api.Service.BatchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/lote")
public class BatchController {
    private final BatchService batchService;
    private GlobalException ge;

    @Autowired
    public BatchController(BatchService batchService) {
        this.batchService = batchService;
    }


    @GetMapping("/selecionar")
    public ResponseEntity<List<Batch>> listarLotes() {
        // Se lançar uma RuntimeException aqui, o Spring vai chamar o método do GlobalException automaticamente
        List<Batch> batches = batchService.listarLotes();
        return ResponseEntity.ok(batches);
    }



    @PostMapping("/inserir")
    public ResponseEntity<?> inserirLote(@RequestBody Batch batch) {
        Batch batchSalvo = batchService.inserirLote(batch);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Lote inserido com sucesso! ID: " + batchSalvo.getId());
    }



    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluirLote(@PathVariable Long id) {
        batchService.excluirLote(id);
        return ResponseEntity.ok("Lote excluído com sucesso!");
    }



    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarLote(@PathVariable Long id, @Valid @RequestBody Batch batchAtualizado) {
        batchService.atualizarLote(id, batchAtualizado);
        return ResponseEntity.ok("Lote atualizado com sucesso!");
    }



    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<?> atualizarLoteParcial(@PathVariable Long id, @Valid @RequestBody Map<String, Object> updates) {
        batchService.atualizarLoteParcial(id, updates);
        return ResponseEntity.ok("Lote atualizado parcialmente com sucesso!");
    }


}

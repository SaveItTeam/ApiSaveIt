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
    public ResponseEntity<List<Batch>> listBatch() {
        List<Batch> batches = batchService.listBatch();
        return ResponseEntity.ok(batches);
    }



    @PostMapping("/inserir")
    public ResponseEntity<?> insertBatch(@RequestBody Batch batch) {
        Batch batchSalvo = batchService.insertBatch(batch);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Lote inserido com sucesso! ID: " + batchSalvo.getId());
    }



    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> deleteBatch(@PathVariable Long id) {
        batchService.deleteBatch(id);
        return ResponseEntity.ok("Lote excluído com sucesso!");
    }



    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> updateBatch(@PathVariable Long id, @Valid @RequestBody Batch batchAtualizado) {
        batchService.updateBatch(id, batchAtualizado);
        return ResponseEntity.ok("Lote atualizado com sucesso!");
    }



    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<?> updateBatchPartial(@PathVariable Long id, @Valid @RequestBody Map<String, Object> updates) {
        batchService.updateBatchPartial(id, updates);
        return ResponseEntity.ok("Lote atualizado parcialmente com sucesso!");
    }


}

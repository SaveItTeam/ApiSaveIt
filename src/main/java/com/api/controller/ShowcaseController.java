package com.api.controller;

import com.api.exception.GlobalException;
import com.api.OpenAPI.ShowcaseOpenApi;
import com.api.Service.ShowcaseService;
import com.api.dto.showcase.ShowcaseListDTO;
import com.api.dto.showcase.ShowcaseRequestDTO;
import com.api.dto.showcase.ShowcaseResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/showcase")
public class ShowcaseController implements ShowcaseOpenApi {
    private final ShowcaseService showCaseService;
    private GlobalException ge;

    @Autowired
    public ShowcaseController(ShowcaseService showCaseService) {
        this.showCaseService = showCaseService;
    }


    @GetMapping("/selecionar")
    public ResponseEntity<List<ShowcaseResponseDTO>> listShowcase() {
        List<ShowcaseResponseDTO> showcases = showCaseService.listShowcase();
        return ResponseEntity.ok(showcases);
    }

    @GetMapping("/selecionarPorEmpresa/{enterpriseId}")
    public ResponseEntity<List<ShowcaseListDTO>> listShowcaseByEnterpriseId(@PathVariable long enterpriseId) {
        List<ShowcaseListDTO> showcaseListDTOS = showCaseService.listShowcaseWithProductByEnterpriseId(enterpriseId);
        return ResponseEntity.ok(showcaseListDTOS);
    }

    @PostMapping("/inserir")
    public ResponseEntity<?> insertShowcase(@RequestBody ShowcaseRequestDTO showCase) {
        ShowcaseResponseDTO showcaseSalvo = showCaseService.insertShowcase(showCase);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Vitrine inserido com sucesso!");
    }



    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> deleteShowcase(@PathVariable Long id) {
        showCaseService.deleteShowcase(id);
        return ResponseEntity.ok("Vitrine excluído com sucesso!");
    }



    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> updateShowcase(@PathVariable Long id, @Valid @RequestBody ShowcaseRequestDTO showcaseAtualizado) {
        showCaseService.updateShowcase(id, showcaseAtualizado);
        return ResponseEntity.ok("Vitrine atualizado com sucesso!");
    }

    @GetMapping("/listarVitrine/{category}")
    public ResponseEntity<List<ShowcaseListDTO>> listShowcasaWithProduct(@PathVariable String category) {
        List<ShowcaseListDTO> showcaseListDTOS = showCaseService.listShowcaseWithProduct(category);
        return ResponseEntity.ok(showcaseListDTOS);
    }



    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<?> updateShowcasePartial(@PathVariable Long id, @Valid @RequestBody Map<String, Object> updates) {
        showCaseService.updateShowcasePartial(id, updates);
        return ResponseEntity.ok("Vitrine atualizado parcialmente com sucesso!");
    }


}

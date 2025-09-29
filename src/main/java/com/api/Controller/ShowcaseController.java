package com.api.Controller;

import com.api.Exception.GlobalException;
import com.api.Model.Showcase;
import com.api.Service.ShowcaseService;
import com.api.dto.showcase.ShowcaseRequestDTO;
import com.api.dto.showcase.ShowcaseResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/showcase")
public class ShowcaseController {
    private final ShowcaseService showCaseService;
    private GlobalException ge;

    @Autowired
    public ShowcaseController(ShowcaseService showCaseService) {
        this.showCaseService = showCaseService;
    }


    @GetMapping("/selecionar")
    @Operation(summary = "Listar todas as vitrines")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de vitrines retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<List<ShowcaseResponseDTO>> listShowcase() {
        List<ShowcaseResponseDTO> showcases = showCaseService.listShowcase();
        return ResponseEntity.ok(showcases);
    }



    @PostMapping("/inserir")
    @Operation(summary = "Inserir uma nova vitrine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vitrine inserida com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<?> insertShowcase(@RequestBody ShowcaseRequestDTO showCase) {
        ShowcaseResponseDTO showcaseSalvo = showCaseService.insertShowcase(showCase);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Vitrine inserido com sucesso!");
    }



    @DeleteMapping("/excluir/{id}")
    @Operation(summary = "Excluir uma vitrine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vitrine excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Vitrine não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<?> deleteShowcase(@PathVariable Long id) {
        showCaseService.deleteShowcase(id);
        return ResponseEntity.ok("Vitrine excluído com sucesso!");
    }



    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar uma vitrine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vitrine atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Vitrine não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<?> updateShowcase(@PathVariable Long id, @Valid @RequestBody ShowcaseRequestDTO showcaseAtualizado) {
        showCaseService.updateShowcase(id, showcaseAtualizado);
        return ResponseEntity.ok("Vitrine atualizado com sucesso!");
    }



    @PatchMapping("/atualizarParcial/{id}")
    @Operation(summary = "Atualizar parcialmente uma vitrine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vitrine atualizado parcialmente com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Vitrine não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<?> updateShowcasePartial(@PathVariable Long id, @Valid @RequestBody Map<String, Object> updates) {
        showCaseService.updateShowcasePartial(id, updates);
        return ResponseEntity.ok("Vitrine atualizado parcialmente com sucesso!");
    }


}

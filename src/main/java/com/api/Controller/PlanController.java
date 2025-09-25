package com.api.Controller;

import com.api.Exception.GlobalException;
import com.api.Model.Plan;
import com.api.Service.PlanService;
import com.api.dto.Plan.PlanRequestDTO;
import com.api.dto.Plan.PlanResponseDTO;
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
@RequestMapping("/api/plan")
public class PlanController {
    private final PlanService planService;
    private GlobalException ge;

    @Autowired
    public PlanController(PlanService planService) {
        this.planService = planService;
    }


    @GetMapping("/selecionar")
    @Operation(summary = "Listar todos os planos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de planos retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<List<PlanResponseDTO>> listPlan() {
        List<PlanResponseDTO> plans = planService.listPlan();
        return ResponseEntity.ok(plans);
    }



    @PostMapping("/inserir")
    @Operation(summary = "Inserir um novo plano")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Plano inserido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<?> insertPlan(@RequestBody PlanRequestDTO plan) {
        planService.insertPlan(plan);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("plano inserido com sucesso!");
    }



    @DeleteMapping("/excluir/{id}")
    @Operation(summary = "Excluir um plano")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plano excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Plano não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<?> deletePlan(@PathVariable Long id) {
        planService.deletePlan(id);
        return ResponseEntity.ok("Plan excluído com sucesso!");
    }



    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar um plano")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plano atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Plano não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<?> updatePlan(@PathVariable Long id, @Valid @RequestBody PlanRequestDTO planAtualizado) {
        planService.updatePlan(id, planAtualizado);
        return ResponseEntity.ok("Plano atualizado com sucesso!");
    }


    @PatchMapping("/atualizarParcial/{id}")
    @Operation(summary = "Atualizar parcialmente um plano")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plano atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Plano não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<?> updatePlanPartial(@PathVariable Long id, @Valid @RequestBody Map<String, Object> updates) {
        planService.updatePlanPartial(id, updates);
        return ResponseEntity.ok("Plano atualizado parcialmente com sucesso!");
    }

}

package com.api.controller;

import com.api.Exception.GlobalException;
import com.api.OpenAPI.PlanOpenApi;
import com.api.Service.PlanService;
import com.api.dto.Plan.PlanRequestDTO;
import com.api.dto.Plan.PlanResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/plan")
public class PlanController implements PlanOpenApi {
    private final PlanService planService;
    private GlobalException ge;

    @Autowired
    public PlanController(PlanService planService) {
        this.planService = planService;
    }


    public ResponseEntity<List<PlanResponseDTO>> listPlan() {
        List<PlanResponseDTO> plans = planService.listPlan();
        return ResponseEntity.ok(plans);
    }

    public ResponseEntity<?> insertPlan(@RequestBody PlanRequestDTO plan) {
        planService.insertPlan(plan);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("plano inserido com sucesso!");
    }

    public ResponseEntity<?> deletePlan(@PathVariable Long id) {
        planService.deletePlan(id);
        return ResponseEntity.ok("Plan excluído com sucesso!");
    }

    public ResponseEntity<?> updatePlan(@PathVariable Long id, @Valid @RequestBody PlanRequestDTO planAtualizado) {
        planService.updatePlan(id, planAtualizado);
        return ResponseEntity.ok("Plano atualizado com sucesso!");
    }


    public ResponseEntity<?> updatePlanPartial(@PathVariable Long id, @Valid @RequestBody Map<String, Object> updates) {
        planService.updatePlanPartial(id, updates);
        return ResponseEntity.ok("Plano atualizado parcialmente com sucesso!");
    }

}

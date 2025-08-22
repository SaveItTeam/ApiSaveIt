package com.api.Controller;

import com.api.Exception.GlobalException;
import com.api.Model.Plan;
import com.api.Service.PlanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<Plan>> listPlan() {
        List<Plan> plans = planService.listPlan();
        return ResponseEntity.ok(plans);
    }



    @PostMapping("/inserir")
    public ResponseEntity<?> insertPlan(@RequestBody Plan plan) {
        Plan planSalvo = planService.insertPlan(plan);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("plano inserido com sucesso! ID: " + planSalvo.getId());
    }



    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> deletePlan(@PathVariable Long id) {
        planService.deletePlan(id);
        return ResponseEntity.ok("Plan excluído com sucesso!");
    }



    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> updatePlan(@PathVariable Long id, @Valid @RequestBody Plan planAtualizado) {
        planService.updatePlan(id, planAtualizado);
        return ResponseEntity.ok("Plano atualizado com sucesso!");
    }



    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<?> updatePlanPartial(@PathVariable Long id, @Valid @RequestBody Map<String, Object> updates) {
        planService.updatePlanPartial(id, updates);
        return ResponseEntity.ok("Plano atualizado parcialmente com sucesso!");
    }

}

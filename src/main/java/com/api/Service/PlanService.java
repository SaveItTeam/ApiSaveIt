package com.api.Service;

import com.api.Model.Plan;
import com.api.Repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
@Service
public class PlanService {
    private final PlanRepository planRepository;

    @Autowired
    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }


    public List<Plan> listPlan(){return planRepository.findAll();}

    public Plan insertPlan(Plan plan) {
        return planRepository.save(plan);
    }

    // Deleção
    public void deletePlan(Long id) {
        planRepository.deleteById(id);
    }
    // Atualização
    public Plan updatePlan(Long id, Plan planAtualizado) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Plan com ID " + id + " não encontrado"));

        plan.setName(planAtualizado.getName());
        plan.setPrice(planAtualizado.getPrice());
        plan.setDescription(planAtualizado.getDescription());

        return planRepository.save(plan);
    }

    public Plan updatePlanPartial(Long id, Map<String, Object> updates) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Plan com ID " + id + " não encontrado"));

        if (updates.containsKey("name")) {
            plan.setName((String) updates.get("name"));
        }
        if (updates.containsKey("price")) {
            plan.setPrice((Double) updates.get("price"));
        }
        if (updates.containsKey("description")) {
            plan.setDescription((String) updates.get("description"));
        }

        return planRepository.save(plan);
    }
}

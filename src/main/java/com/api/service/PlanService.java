package com.api.service;

import com.api.model.Plan;
import com.api.repository.PlanRepository;
import com.api.dto.plan.PlanRequestDTO;
import com.api.dto.plan.PlanResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
@Service
public class  PlanService {
    private final PlanRepository planRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public PlanService(PlanRepository planRepository, ObjectMapper objectMapper) {
        this.planRepository = planRepository;
        this.objectMapper = objectMapper;
    }


    public List<PlanResponseDTO> listPlan(){
        List<Plan> plans = planRepository.findAll();
        List<PlanResponseDTO> plansResponse = new ArrayList<>();
        for (Plan plan : plans) {
            plansResponse.add(objectMapper.convertValue(plan, PlanResponseDTO.class));
        }
        return plansResponse;
    }

    public void insertPlan(PlanRequestDTO plan) {
        Plan planRequest = objectMapper.convertValue(plan, Plan.class);
        planRepository.save(planRequest);
    }

    // Deleção
    public void deletePlan(Long id) {
        planRepository.deleteById(id);
    }
    // Atualização
    public Plan updatePlan(Long id, PlanRequestDTO planAtualizado) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Plan com ID " + id + " não encontrado"));

        plan.setName(planAtualizado.getName());
        plan.setPrice(planAtualizado.getPrice());
        plan.setDescription(planAtualizado.getDescription());

        return planRepository.save(plan);
    }

    public PlanResponseDTO updatePlanPartial(Long id, Map<String, Object> updates) {
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

        planRepository.save(plan);
        return objectMapper.convertValue(plan, PlanResponseDTO.class);
    }
}

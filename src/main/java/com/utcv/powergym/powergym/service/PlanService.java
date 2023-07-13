package com.utcv.powergym.powergym.service;

import com.utcv.powergym.powergym.dto.PlanDTO;
import com.utcv.powergym.powergym.entity.Plan;

import java.util.List;

public interface PlanService {

    Plan loadPlanById(Long planId);

    Plan loadPlanByPlanName(String planName);

    PlanDTO createPlan(PlanDTO planDTO);

    PlanDTO updatePlan(PlanDTO planDTO);

    void removePlan(Long planId);

    List<Plan> getAllPlans();

    List<PlanDTO> getAllPlansDTO();

    PlanDTO loadPlanDTOByPlanName(String planName);
}

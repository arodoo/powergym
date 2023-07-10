package com.utcv.powergym.powergym.service;

import com.utcv.powergym.powergym.dto.PlanDTO;
import com.utcv.powergym.powergym.entity.Plan;

public interface PlanService {

    Plan loadPlanById(Long planId);

    PlanDTO createPlan(PlanDTO planDTO);

    PlanDTO updatePlan(PlanDTO planDTO);

    void removePlan(Long planId);



}

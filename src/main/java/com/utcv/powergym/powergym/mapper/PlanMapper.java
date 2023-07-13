package com.utcv.powergym.powergym.mapper;

import com.utcv.powergym.powergym.dto.PlanDTO;
import com.utcv.powergym.powergym.entity.Plan;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanMapper {

    public PlanDTO fromPlan(Plan plan){
        PlanDTO planDTO = new PlanDTO();
        BeanUtils.copyProperties(plan, planDTO);
        return planDTO;
    }

    public Plan fromPlanDTO(PlanDTO planDTO){
        Plan plan = new Plan();
        BeanUtils.copyProperties(planDTO, plan);
        return plan;
    }

    public List<PlanDTO> fromPlans(List<Plan> all) {
        List<PlanDTO> planDTOS = new java.util.ArrayList<>();
        for (Plan plan : all) {
            planDTOS.add(fromPlan(plan));
        }
        return planDTOS;
    }
}

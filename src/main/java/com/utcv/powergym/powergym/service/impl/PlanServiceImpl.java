package com.utcv.powergym.powergym.service.impl;

import com.utcv.powergym.powergym.dao.PlanDao;
import com.utcv.powergym.powergym.dto.PlanDTO;
import com.utcv.powergym.powergym.entity.Plan;
import com.utcv.powergym.powergym.mapper.PlanMapper;
import com.utcv.powergym.powergym.service.PlanService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PlanServiceImpl implements PlanService {

    private PlanDao planDao;

    private PlanMapper planMapper;

    public PlanServiceImpl(PlanDao planDao, PlanMapper planMapper) {
        this.planDao = planDao;
        this.planMapper = planMapper;
    }
    @Override
    public Plan loadPlanById(Long planId) {
        return planDao.findById(planId).orElseThrow(() -> new EntityNotFoundException("Plan not found"));
    }

    @Override
    public Plan loadPlanByPlanName(String planName) {
        return planDao.findByPlanName(planName);
    }

    @Override
    public PlanDTO createPlan(PlanDTO planDTO) {
        Plan plan = planMapper.fromPlanDTO(planDTO);
        planDao.save(plan);
        return planMapper.fromPlan(plan);
    }

    @Override
    public PlanDTO updatePlan(PlanDTO planDTO) {
        Plan loadedPlan = loadPlanById(planDTO.getPlanId());
        Plan plan = planMapper.fromPlanDTO(planDTO);
        plan.setPlanName(loadedPlan.getPlanName());
        plan.setPrice(loadedPlan.getPrice());
        plan.setDescription(loadedPlan.getDescription());
        plan.setNumDays(loadedPlan.getNumDays());
        planDao.save(plan);
        return planMapper.fromPlan(plan);
    }

    @Override
    public void removePlan(Long planId) {
        planDao.deleteById(planId);
    }

    @Override
    public List<Plan> getAllPlans() {
        return planDao.findAll();
    }

    @Override
    public List<PlanDTO> getAllPlansDTO() {
        return planMapper.fromPlans(planDao.findAll());
    }

    @Override
    public PlanDTO loadPlanDTOByPlanName(String planName) {
        return planMapper.fromPlan(planDao.findByPlanName(planName));
    }


}

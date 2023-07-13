package com.utcv.powergym.powergym.web;

import com.utcv.powergym.powergym.dao.PlanDao;
import com.utcv.powergym.powergym.dto.PlanDTO;
import com.utcv.powergym.powergym.entity.Plan;
import com.utcv.powergym.powergym.service.PlanService;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/plans")
@CrossOrigin("*")
public class PlanRestController {

    private PlanService planService;

    private PlanDao planDao;

    public PlanRestController(PlanService planService, PlanDao planDao) {
        this.planService = planService;
        this.planDao = planDao;
    }

    @GetMapping
    public List<PlanDTO> getAllPlans() {
        return planService.getAllPlansDTO();
    }

    @GetMapping("/find")
    public PlanDTO getPlan(@RequestParam(name = "planName", defaultValue = "") String planName) {
        return planService.loadPlanDTOByPlanName(planName);
    }

    @PostMapping
    public PlanDTO savePlan(@RequestBody PlanDTO planDTO) {
        return planService.createPlan(planDTO);
    }

    @PutMapping("/{planId}")
    public PlanDTO updatePlan(@RequestBody PlanDTO planDTO, @PathVariable Long planId) {
        planDTO.setPlanId(planId);
        return planService.updatePlan(planDTO);
    }

    @DeleteMapping("/{planId}")
    public void deletePlan(@PathVariable Long planId){
        planDao.findById(planId).orElseThrow(() -> new EntityNotFoundException("Plan with id" + planId + " not found"));
        planService.removePlan(planId);
    }
}

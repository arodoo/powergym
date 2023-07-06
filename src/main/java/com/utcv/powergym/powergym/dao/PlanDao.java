package com.utcv.powergym.powergym.dao;

import com.utcv.powergym.powergym.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanDao extends JpaRepository<Plan, Long> {
    Plan findByPlanName(String planName);
}

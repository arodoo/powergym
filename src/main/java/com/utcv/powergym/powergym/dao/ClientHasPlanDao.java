package com.utcv.powergym.powergym.dao;

import com.utcv.powergym.powergym.entity.ClientHasPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientHasPlanDao extends JpaRepository<ClientHasPlan, Long> {
}

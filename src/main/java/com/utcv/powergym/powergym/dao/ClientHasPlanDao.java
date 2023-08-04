package com.utcv.powergym.powergym.dao;

import com.utcv.powergym.powergym.entity.Client;
import com.utcv.powergym.powergym.entity.ClientHasPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientHasPlanDao extends JpaRepository<ClientHasPlan, Long> {
    @Query("FROM ClientHasPlan c WHERE c.client.clientId = :clientId")
    List<ClientHasPlan> findByClientId(Long clientId);
}

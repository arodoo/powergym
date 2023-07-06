package com.utcv.powergym.powergym.dao;

import com.utcv.powergym.powergym.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDao extends JpaRepository<Client, Long> {
}

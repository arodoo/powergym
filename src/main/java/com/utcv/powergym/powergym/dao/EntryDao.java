package com.utcv.powergym.powergym.dao;

import com.utcv.powergym.powergym.entity.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EntryDao extends JpaRepository<Entry, Long> {

    @Query("FROM Entry e WHERE e.client.clientId = :clientId")
    List<Entry> findByClientId(Long clientId);
}

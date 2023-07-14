package com.utcv.powergym.powergym.service;

import com.utcv.powergym.powergym.dto.ClientHasPlanDTO;
import com.utcv.powergym.powergym.entity.ClientHasPlan;

import java.util.List;

public interface ClientHasPlanService {

    ClientHasPlanDTO addPlanToClient(ClientHasPlanDTO clientHasPlanDTO);


    List<ClientHasPlanDTO> getAllClientHasPlansDTO();

    void removeClientHasPlan(Long clientHasPlanId);
}

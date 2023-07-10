package com.utcv.powergym.powergym.service;

import com.utcv.powergym.powergym.dto.ClientHasPlanDTO;
import com.utcv.powergym.powergym.entity.ClientHasPlan;

public interface ClientHasPlanService {

    ClientHasPlan addPlanToClient(ClientHasPlanDTO clientHasPlanDTO);


}

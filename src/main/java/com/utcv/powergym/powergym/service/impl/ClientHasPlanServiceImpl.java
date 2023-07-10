package com.utcv.powergym.powergym.service.impl;

import com.utcv.powergym.powergym.dao.ClientHasPlanDao;
import com.utcv.powergym.powergym.dto.ClientHasPlanDTO;
import com.utcv.powergym.powergym.entity.ClientHasPlan;
import com.utcv.powergym.powergym.mapper.ClientHasPlanMapper;
import com.utcv.powergym.powergym.service.ClientHasPlanService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ClientHasPlanServiceImpl implements ClientHasPlanService {


    private ClientHasPlanMapper clientHasPlanMapper;

    private ClientHasPlanDao clientHasPlanDao;

    public ClientHasPlanServiceImpl(ClientHasPlanMapper clientHasPlanMapper) {
        this.clientHasPlanMapper = clientHasPlanMapper;
    }
    @Override
    public ClientHasPlan addPlanToClient(ClientHasPlanDTO clientHasPlanDTO) {
        ClientHasPlan clientHasPlan = clientHasPlanMapper.fromClientHasPlanDTO(clientHasPlanDTO);
        clientHasPlanDao.save(clientHasPlan);
        return clientHasPlan;
    }


}
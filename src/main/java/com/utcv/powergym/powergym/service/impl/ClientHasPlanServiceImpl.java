package com.utcv.powergym.powergym.service.impl;

import com.utcv.powergym.powergym.dao.ClientHasPlanDao;
import com.utcv.powergym.powergym.dto.ClientHasPlanDTO;
import com.utcv.powergym.powergym.entity.ClientHasPlan;
import com.utcv.powergym.powergym.mapper.ClientHasPlanMapper;
import com.utcv.powergym.powergym.service.ClientHasPlanService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ClientHasPlanServiceImpl implements ClientHasPlanService {


    private ClientHasPlanMapper clientHasPlanMapper;

    private ClientHasPlanDao clientHasPlanDao;

    public ClientHasPlanServiceImpl(ClientHasPlanMapper clientHasPlanMapper, ClientHasPlanDao clientHasPlanDao) {
        this.clientHasPlanMapper = clientHasPlanMapper;
        this.clientHasPlanDao = clientHasPlanDao;
    }

    @Override
    public ClientHasPlanDTO addPlanToClient(ClientHasPlanDTO clientHasPlanDTO) {
        ClientHasPlan clientHasPlan = new ClientHasPlan();
        clientHasPlan = clientHasPlanMapper.fromClientHasPlanDTO(clientHasPlanDTO);
        clientHasPlanDao.save(clientHasPlan);
        return clientHasPlanMapper.fromClientHasPlan(clientHasPlan);
    }

    @Override
    public List<ClientHasPlanDTO> getAllClientHasPlansDTO() {
        return clientHasPlanMapper.fromClientHasPlans(clientHasPlanDao.findAll());
    }


}

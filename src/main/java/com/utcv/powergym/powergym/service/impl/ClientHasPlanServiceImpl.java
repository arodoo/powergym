package com.utcv.powergym.powergym.service.impl;

import com.utcv.powergym.powergym.dao.ClientHasPlanDao;
import com.utcv.powergym.powergym.dto.ClientHasPlanDTO;
import com.utcv.powergym.powergym.entity.Client;
import com.utcv.powergym.powergym.entity.ClientHasPlan;
import com.utcv.powergym.powergym.entity.Plan;
import com.utcv.powergym.powergym.entity.User;
import com.utcv.powergym.powergym.mapper.ClientHasPlanMapper;
import com.utcv.powergym.powergym.mapper.ClientMapper;
import com.utcv.powergym.powergym.mapper.PlanMapper;
import com.utcv.powergym.powergym.mapper.UserMapper;
import com.utcv.powergym.powergym.service.ClientHasPlanService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ClientHasPlanServiceImpl implements ClientHasPlanService {


    private ClientHasPlanMapper clientHasPlanMapper;

    private ClientHasPlanDao clientHasPlanDao;

    private ClientMapper clientMapper;
    private PlanMapper planMapper;
    private UserMapper userMapper;

    public ClientHasPlanServiceImpl(ClientHasPlanMapper clientHasPlanMapper, ClientHasPlanDao clientHasPlanDao, ClientMapper clientMapper, PlanMapper planMapper, UserMapper userMapper) {
        this.clientHasPlanMapper = clientHasPlanMapper;
        this.clientHasPlanDao = clientHasPlanDao;
        this.clientMapper = clientMapper;
        this.planMapper = planMapper;
        this.userMapper = userMapper;
    }

    @Override
    public ClientHasPlanDTO addPlanToClient(ClientHasPlanDTO clientHasPlanDTO) {
        ClientHasPlan clientHasPlan;
        clientHasPlan = clientHasPlanMapper.fromClientHasPlanDTO(clientHasPlanDTO);

        Client client = clientMapper.fromClientDTO(clientHasPlanDTO.getClient());
        Plan plan = planMapper.fromPlanDTO(clientHasPlanDTO.getPlan());
        User user = userMapper.fromUserDTO(clientHasPlanDTO.getUser());

        clientHasPlan.assignPlanToClient(plan, user, client);
        clientHasPlanDao.save(clientHasPlan);
        return clientHasPlanMapper.fromClientHasPlan(clientHasPlan);
    }

    @Override
    public List<ClientHasPlanDTO> getAllClientHasPlansDTO() {
        return clientHasPlanMapper.fromClientHasPlans(clientHasPlanDao.findAll());
    }

    @Override
    public void removeClientHasPlan(Long clientHasPlanId) {
        clientHasPlanDao.deleteById(clientHasPlanId);
    }


}

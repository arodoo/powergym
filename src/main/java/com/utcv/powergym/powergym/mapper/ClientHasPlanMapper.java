package com.utcv.powergym.powergym.mapper;

import com.utcv.powergym.powergym.dto.ClientHasPlanDTO;
import com.utcv.powergym.powergym.entity.ClientHasPlan;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ClientHasPlanMapper {

    private ClientMapper clientMapper;

    private PlanMapper planMapper;

    private UserMapper userMapper;

    public ClientHasPlanMapper(ClientMapper clientMapper, PlanMapper planMapper, UserMapper userMapper) {
        this.clientMapper = clientMapper;
        this.planMapper = planMapper;
        this.userMapper = userMapper;
    }

    public ClientHasPlanDTO fromClientHasPlan(ClientHasPlan clientHasPlan) {
        ClientHasPlanDTO clientHasPlanDTO = new ClientHasPlanDTO();
        BeanUtils.copyProperties(clientHasPlan, clientHasPlanDTO);
        return clientHasPlanDTO;
    }

    public ClientHasPlan fromClientHasPlanDTO(ClientHasPlanDTO clientHasPlanDTO) {
        ClientHasPlan clientHasPlan = new ClientHasPlan();
        BeanUtils.copyProperties(clientHasPlanDTO, clientHasPlan);
        return clientHasPlan;
    }
}

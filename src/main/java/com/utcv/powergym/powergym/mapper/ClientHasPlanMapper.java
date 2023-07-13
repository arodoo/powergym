package com.utcv.powergym.powergym.mapper;

import com.utcv.powergym.powergym.dto.ClientHasPlanDTO;
import com.utcv.powergym.powergym.entity.ClientHasPlan;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

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
        ClientHasPlanDTO clientHasPlanDTO = new ClientHasPlanDTO(); // Copiar solo las propiedades simples de ClientHasPlan
        clientHasPlanDTO.setContractId(clientHasPlan.getContractId());
        clientHasPlanDTO.setContractDate(clientHasPlan.getContractDate());
        clientHasPlanDTO.setStartDate(clientHasPlan.getStartDate());
        clientHasPlanDTO.setEndDate(clientHasPlan.getEndDate());
        clientHasPlanDTO.setIsActive(clientHasPlan.isActive()); // Usar los mappers para copiar las propiedades complejas de User, Client y Plan
        clientHasPlanDTO.setUser(userMapper.fromUser(clientHasPlan.getUser()));
        clientHasPlanDTO.setClient(clientMapper.fromClient(clientHasPlan.getClient()));
        clientHasPlanDTO.setPlan(planMapper.fromPlan(clientHasPlan.getPlan()));
        return clientHasPlanDTO;
    }


    public ClientHasPlan fromClientHasPlanDTO(ClientHasPlanDTO clientHasPlanDTO) {
        ClientHasPlan clientHasPlan = new ClientHasPlan();
        try {
            BeanUtils.copyProperties(clientHasPlanDTO, clientHasPlan);
            clientHasPlan.setUser(userMapper.fromUserDTO(clientHasPlanDTO.getUser()));
            clientHasPlan.setClient(clientMapper.fromClientDTO(clientHasPlanDTO.getClient()));
            clientHasPlan.setPlan(planMapper.fromPlanDTO(clientHasPlanDTO.getPlan()));
            return clientHasPlan;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ClientHasPlanDTO> fromClientHasPlans(List<ClientHasPlan> all) {
        List<ClientHasPlanDTO> clientHasPlanDTOS = new java.util.ArrayList<>();
        for (ClientHasPlan clientHasPlan : all) {
            clientHasPlanDTOS.add(fromClientHasPlan(clientHasPlan));
        }
        return clientHasPlanDTOS;
    }
}

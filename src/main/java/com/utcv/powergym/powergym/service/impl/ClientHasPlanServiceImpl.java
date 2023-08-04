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
import com.utcv.powergym.powergym.service.ClientService;
import com.utcv.powergym.powergym.service.PlanService;
import com.utcv.powergym.powergym.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class ClientHasPlanServiceImpl implements ClientHasPlanService {


    private ClientHasPlanMapper clientHasPlanMapper;

    private ClientHasPlanDao clientHasPlanDao;

    private ClientMapper clientMapper;
    private PlanMapper planMapper;
    private UserMapper userMapper;

    private ClientService clientService;
    private PlanService planService;
    private UserService userService;

    public ClientHasPlanServiceImpl(ClientHasPlanMapper clientHasPlanMapper,
                                    ClientHasPlanDao clientHasPlanDao,
                                    ClientMapper clientMapper,
                                    PlanMapper planMapper,
                                    UserMapper userMapper,
                                    ClientService clientService,
                                    PlanService planService,
                                    UserService userService) {
        this.clientHasPlanMapper = clientHasPlanMapper;
        this.clientHasPlanDao = clientHasPlanDao;
        this.clientMapper = clientMapper;
        this.planMapper = planMapper;
        this.userMapper = userMapper;
        this.clientService = clientService;
        this.planService = planService;
        this.userService = userService;
    }

    @Override
    public ClientHasPlanDTO addPlanToClient(ClientHasPlanDTO clientHasPlanDTO) {
        ClientHasPlan clientHasPlan;
        clientHasPlan = clientHasPlanMapper.fromClientHasPlanDTO(clientHasPlanDTO);

        try {
            Client client = clientService.loadClientById(clientHasPlanDTO.getClient().getClientId());
            Plan plan = planService.loadPlanById(clientHasPlanDTO.getPlan().getPlanId());
            User user = userService.loadUserById(clientHasPlanDTO.getUser().getUserId());
            clientHasPlan.assignPlanToClient(plan, user, client);
            clientHasPlanDao.save(clientHasPlan);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return clientHasPlanMapper.fromClientHasPlan(clientHasPlan);
    }

    @Override
    public List<ClientHasPlanDTO> getAllClientHasPlansDTO() {
        // Obtenemos la lista de ClientHasPlanDTO
        List<ClientHasPlanDTO> list = clientHasPlanMapper.fromClientHasPlans(clientHasPlanDao.findAll());
        // Ordenamos la lista de forma descendente por el contractId
        Collections.sort(list, new Comparator<ClientHasPlanDTO>() {
            @Override
            public int compare(ClientHasPlanDTO o1, ClientHasPlanDTO o2) {
                // Comparamos los contractId de forma inversa
                return o2.getContractId().compareTo(o1.getContractId());
            }
        });
        // Devolvemos la lista ordenada
        return list;
    }


    @Override
    public void removeClientHasPlan(Long clientHasPlanId) {
        clientHasPlanDao.deleteById(clientHasPlanId);
    }

    @Override
    public List<ClientHasPlanDTO> findByClientId(Long clientId) {
        List<ClientHasPlanDTO> list = clientHasPlanMapper.fromClientHasPlans(clientHasPlanDao.findByClientId(clientId));
        Collections.sort(list, new Comparator<ClientHasPlanDTO>() {
            @Override
            public int compare(ClientHasPlanDTO o1, ClientHasPlanDTO o2) {
                return o2.getContractId().compareTo(o1.getContractId());
            }
        });
        return list;
    }


}

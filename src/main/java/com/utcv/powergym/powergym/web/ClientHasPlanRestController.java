package com.utcv.powergym.powergym.web;

import com.utcv.powergym.powergym.dao.ClientHasPlanDao;
import com.utcv.powergym.powergym.dto.ClientHasPlanDTO;
import com.utcv.powergym.powergym.service.ClientHasPlanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientHasPlans")
@CrossOrigin("*")
public class ClientHasPlanRestController {

    private ClientHasPlanService clientHasPlanService;

    private ClientHasPlanDao clientHasPlanDao;

    public ClientHasPlanRestController(ClientHasPlanService clientHasPlanService, ClientHasPlanDao clientHasPlanDao) {
        this.clientHasPlanService = clientHasPlanService;
        this.clientHasPlanDao = clientHasPlanDao;
    }

    @GetMapping
    public List<ClientHasPlanDTO> getAllClientHasPlans() {
        return clientHasPlanService.getAllClientHasPlansDTO();
    }

    @PostMapping
    public ClientHasPlanDTO addPlanToClient(@RequestBody ClientHasPlanDTO clientHasPlanDTO) {
        return clientHasPlanService.addPlanToClient(clientHasPlanDTO);
    }
}

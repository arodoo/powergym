package com.utcv.powergym.powergym.web;

import com.utcv.powergym.powergym.dao.ClientHasPlanDao;
import com.utcv.powergym.powergym.dto.ClientHasPlanDTO;
import com.utcv.powergym.powergym.service.ClientHasPlanService;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
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
    public ClientHasPlanDTO ClientHasPlanDTO(@RequestBody ClientHasPlanDTO clientHasPlanDTO) {
        return clientHasPlanService.addPlanToClient(clientHasPlanDTO);
    }

    @DeleteMapping("/{clientHasPlanId}")
    public void deleteClientHasPlan(@PathVariable Long clientHasPlanId) {
        clientHasPlanDao.findById(clientHasPlanId).orElseThrow(() -> new EntityNotFoundException("ClientHasPlan with id" + clientHasPlanId + " not found"));
        clientHasPlanService.removeClientHasPlan(clientHasPlanId);
    }

    @GetMapping("/history/{clientId}")
    public List<ClientHasPlanDTO> getClientHasPlanHistory(@PathVariable Long clientId) {
        return clientHasPlanService.findByClientId(clientId);
        // Usar el método findByClientId del repositorio para obtener la lista de entradas de la tabla client_has_plan para el cliente dado
        //return clientHasPlanRepository.findByClientId(clientId);
    }
}

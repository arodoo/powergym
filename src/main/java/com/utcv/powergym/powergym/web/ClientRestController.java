package com.utcv.powergym.powergym.web;

import com.utcv.powergym.powergym.dao.ClientDao;
import com.utcv.powergym.powergym.dto.ClientDTO;
import com.utcv.powergym.powergym.entity.Client;
import com.utcv.powergym.powergym.service.ClientService;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/clients")
@CrossOrigin("*")
public class ClientRestController {

    private ClientService clientService;

    private ClientDao clientDao;

    public ClientRestController(ClientService clientService, ClientDao clientDao) {
        this.clientService = clientService;
        this.clientDao = clientDao;
    }

    @GetMapping
    public List<ClientDTO> getAllClients() {
        return clientService.getAllClientsDTO();
    }

    @GetMapping("/find")
    public ClientDTO getClient(@RequestParam(name = "lastname", defaultValue = "") String lastname) {
        return clientService.loadClientDTOByClientLastName(lastname);
    }

    @PostMapping
    public ClientDTO saveClient(@RequestBody ClientDTO clientDTO) {
        return clientService.createClient(clientDTO);
    }

    @PutMapping("/{clientId}")
    public ClientDTO updateClient(@RequestBody ClientDTO clientDTO, @PathVariable Long clientId) {
        clientDTO.setClientId(clientId);
        return clientService.updateClient(clientDTO);
    }

    @DeleteMapping("/{clientId}")
    public void deleteClient(@PathVariable Long clientId) {
        clientDao.findById(clientId).orElseThrow(() -> new EntityNotFoundException("Client with id" + clientId + " not found"));
        clientService.removeClient(clientId);
    }

}

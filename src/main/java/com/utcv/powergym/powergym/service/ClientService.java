package com.utcv.powergym.powergym.service;

import com.utcv.powergym.powergym.dto.ClientDTO;
import com.utcv.powergym.powergym.entity.Client;

import java.util.List;

public interface ClientService {

    Client loadClientById(Long clientId);

    Client loadClientByClientLastName(String clientLastName);

    ClientDTO createClient(ClientDTO clientDTO);

    ClientDTO updateClient(ClientDTO clientDTO);

    void removeClient(Long clientId);

    List<Client> getAllClients();

    ClientDTO loadClientDTOByClientLastName(String clientLastName);

    List<ClientDTO> getAllClientsDTO();

    ClientDTO loadClientDTOByClientId(Long clientId);
}

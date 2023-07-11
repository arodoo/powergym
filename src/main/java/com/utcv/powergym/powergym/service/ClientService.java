package com.utcv.powergym.powergym.service;

import com.utcv.powergym.powergym.dto.ClientDTO;
import com.utcv.powergym.powergym.entity.Client;

public interface ClientService {

    Client loadClientById(Long clientId);

    Client loadClientByClientLastName(String clientLastName);

    ClientDTO createClient(ClientDTO clientDTO);

    ClientDTO updateClient(ClientDTO clientDTO);

    void removeClient(Long clientId);

}

package com.utcv.powergym.powergym.service.impl;

import com.utcv.powergym.powergym.dao.ClientDao;
import com.utcv.powergym.powergym.dto.ClientDTO;
import com.utcv.powergym.powergym.entity.Client;
import com.utcv.powergym.powergym.mapper.ClientMapper;
import com.utcv.powergym.powergym.service.ClientService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private ClientDao clientDao;

    private ClientMapper clientMapper;

    public ClientServiceImpl(ClientDao clientDao, ClientMapper clientMapper) {
        this.clientDao = clientDao;
        this.clientMapper = clientMapper;
    }

    @Override
    public Client loadClientById(Long clientId) {
        return clientDao.findById(clientId).orElseThrow(()-> new EntityNotFoundException("Client with ID" + clientId + "not found"));
    }

    @Override
    public Client loadClientByClientName(String clientName) {
        return null;
    }

    @Override
    public ClientDTO createClient(ClientDTO clientDTO) {
        Client client = clientMapper.fromClientDTO(clientDTO);
        try {
            clientDao.save(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientMapper.fromClient(client);
    }

    //Check this method
    @Override
    public ClientDTO updateClient(ClientDTO clientDTO) {
        Client loadedClient = loadClientById(clientDTO.getClientId());
        Client client = clientMapper.fromClientDTO(clientDTO);
        Client updatedClient = clientDao.save(client);
        return clientMapper.fromClient(updatedClient);
    }


    //Check this method
    @Override
    public void removeClient(Long clientId) {
        Client client = loadClientById(clientId);
        clientDao.delete(client);
    }
}

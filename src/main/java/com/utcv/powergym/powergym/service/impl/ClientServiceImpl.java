package com.utcv.powergym.powergym.service.impl;

import com.utcv.powergym.powergym.dao.ClientDao;
import com.utcv.powergym.powergym.dto.ClientDTO;
import com.utcv.powergym.powergym.dto.ClientHasPlanDTO;
import com.utcv.powergym.powergym.entity.Client;
import com.utcv.powergym.powergym.mapper.ClientMapper;
import com.utcv.powergym.powergym.service.ClientService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
        return clientDao.findById(clientId).orElseThrow(() -> new EntityNotFoundException("Client with ID" + clientId + "not found"));
    }

    @Override
    public Client loadClientByClientLastName(String clientLastName) {
        return (clientDao.findByLastName(clientLastName));
    }

    @Override
    public ClientDTO createClient(ClientDTO clientDTO) {
        Client client = clientMapper.fromClientDTO(clientDTO);
        try {
            client.setActive(true);
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

    @Override
    public List<Client> getAllClients() {
        return clientDao.findAll();
    }


    @Override
    public ClientDTO loadClientDTOByClientLastName(String clientLastName) {
        return clientMapper.fromClient((clientDao.findByLastName(clientLastName)));
    }

    @Override
    public List<ClientDTO> getAllClientsDTO() {
        //return clientMapper.fromClients(clientDao.findAll());
        List<ClientDTO> list = clientMapper.fromClients(clientDao.findAll());
        Collections.sort(list, new Comparator<ClientDTO>() {
            @Override
            public int compare(ClientDTO o1, ClientDTO o2) {
                return o2.getClientId().compareTo(o1.getClientId());
            }
        });
        return list;
    }


}

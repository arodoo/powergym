package com.utcv.powergym.powergym.mapper;

import com.utcv.powergym.powergym.dto.ClientDTO;
import com.utcv.powergym.powergym.entity.Client;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientMapper {

    public ClientDTO fromClient(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        BeanUtils.copyProperties(client, clientDTO);
        return clientDTO;
    }

    public Client fromClientDTO(ClientDTO clientDTO) {
        Client client = new Client();
        BeanUtils.copyProperties(clientDTO, client);
        return client;
    }

    public List<ClientDTO> fromClients(List<Client> all) {
        List<ClientDTO> clientDTOS = new java.util.ArrayList<>();
        for (Client client : all) {
            clientDTOS.add(fromClient(client));
        }
        return clientDTOS;
    }
}

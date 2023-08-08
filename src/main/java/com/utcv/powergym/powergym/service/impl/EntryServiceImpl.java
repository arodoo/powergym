package com.utcv.powergym.powergym.service.impl;

import com.utcv.powergym.powergym.dao.EntryDao;
import com.utcv.powergym.powergym.dto.EntryDTO;
import com.utcv.powergym.powergym.entity.Client;
import com.utcv.powergym.powergym.entity.Entry;
import com.utcv.powergym.powergym.mapper.ClientMapper;
import com.utcv.powergym.powergym.mapper.EntryMapper;
import com.utcv.powergym.powergym.service.ClientService;
import com.utcv.powergym.powergym.service.EntryService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class EntryServiceImpl implements EntryService {

    private EntryMapper entryMapper;
    private EntryDao entryDao;

    private ClientMapper clientMapper;
    private ClientService clientService;

    public EntryServiceImpl(EntryMapper entryMapper, EntryDao entryDao, ClientMapper clientMapper, ClientService clientService) {
        this.entryMapper = entryMapper;
        this.entryDao = entryDao;
        this.clientMapper = clientMapper;
        this.clientService = clientService;
    }
    @Override
    public EntryDTO addEntryToClient(EntryDTO entryDTO) {
        Entry entry;
        entry = entryMapper.fromEntryDTO(entryDTO);
        Date date = new Date();

        try {
            Client client = clientService.loadClientById(entryDTO.getClient().getClientId());
            entry.setClient(client);
            entry.setEntryDate(date);
            entry = entryDao.save(entry);
            return entryMapper.fromEntry(entry);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return entryMapper.fromEntry(entry);
    }

    @Override
    public EntryDTO addEntryToClientWithId(Long clientId) {
        Entry entry = new Entry();
        Date date = new Date();
        try {
            Client client = clientService.loadClientById(clientId);
            entry.setClient(client);
            entry.setEntryDate(date);
            entry = entryDao.save(entry);
            return entryMapper.fromEntry(entry);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return entryMapper.fromEntry(entry);
    }

    @Override
    public List<EntryDTO> getAllEntriesDTO() {
        List<EntryDTO> list = entryMapper.fromEntries(entryDao.findAll());
        Collections.sort(list, new Comparator<EntryDTO>() {
            @Override
            public int compare(EntryDTO o1, EntryDTO o2) {
                return o2.getEntryDate().compareTo(o1.getEntryDate());
            }
        });
        return list;
    }

    @Override
    public void removeEntry(Long entryId) {
        entryDao.deleteById(entryId);
    }

    @Override
    public List<EntryDTO> findEntriesByClientId(Long clientId) {
        List<EntryDTO> list = entryMapper.fromEntries(entryDao.findByClientId(clientId));
        Collections.sort(list, new Comparator<EntryDTO>() {
            @Override
            public int compare(EntryDTO o1, EntryDTO o2) {
                return o2.getEntryDate().compareTo(o1.getEntryDate());
            }
        });
        return list;
    }
}

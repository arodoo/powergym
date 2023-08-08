package com.utcv.powergym.powergym.mapper;

import com.utcv.powergym.powergym.dto.EntryDTO;
import com.utcv.powergym.powergym.entity.Entry;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryMapper {

    private ClientMapper clientMapper;

    public EntryMapper(ClientMapper clientMapper) {
        this.clientMapper = clientMapper;
    }

    public EntryDTO fromEntry(Entry entry) {
        EntryDTO entryDTO = new EntryDTO();
        entryDTO.setEntryId(entry.getEntryId());
        entryDTO.setClient(clientMapper.fromClient(entry.getClient()));
        entryDTO.setEntryDate(entry.getEntryDate());
        return entryDTO;
    }

    public Entry fromEntryDTO(EntryDTO entryDTO) {
        Entry entry = new Entry();
        entry.setEntryId(entryDTO.getEntryId());
        entry.setClient(clientMapper.fromClientDTO(entryDTO.getClient()));
        entry.setEntryDate(entryDTO.getEntryDate());
        return entry;
    }

    public List<EntryDTO> fromEntries(List<Entry> all) {
        List<EntryDTO> entryDTOS = new java.util.ArrayList<>();
        for (Entry entry : all) {
            entryDTOS.add(fromEntry(entry));
        }
        return entryDTOS;
    }
}

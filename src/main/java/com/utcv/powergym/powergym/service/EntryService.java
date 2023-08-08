package com.utcv.powergym.powergym.service;

import com.utcv.powergym.powergym.dto.EntryDTO;

import java.util.List;

public interface EntryService {

    EntryDTO addEntryToClient(EntryDTO entryDTO);

    EntryDTO addEntryToClientWithId(Long clientId);

    List<EntryDTO> getAllEntriesDTO();

    void removeEntry(Long entryId);

    List<EntryDTO> findEntriesByClientId(Long clientId);
}

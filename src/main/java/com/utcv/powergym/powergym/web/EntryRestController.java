package com.utcv.powergym.powergym.web;

import com.utcv.powergym.powergym.dto.EntryDTO;
import com.utcv.powergym.powergym.service.EntryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entries")
@CrossOrigin("*")
public class EntryRestController {

    private EntryService entryService;

    public EntryRestController(EntryService entryService) {
        this.entryService = entryService;
    }

    @GetMapping
    public List<EntryDTO> getAllEntries() {
        return entryService.getAllEntriesDTO();
    }

    @GetMapping("/history/{clientId}")
    public List<EntryDTO> getEntryHistoryByClientId(@PathVariable Long clientId) {
        return entryService.findEntriesByClientId(clientId);
    }

    @GetMapping("/add/{clientId}")
    public EntryDTO addEntry(@PathVariable Long clientId) {
        return entryService.addEntryToClientWithId(clientId);
    }

    @DeleteMapping("/{entryId}")
    public void deleteEntry(@PathVariable Long entryId) {
        entryService.removeEntry(entryId);
    }
}

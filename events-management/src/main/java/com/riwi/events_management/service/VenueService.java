package com.riwi.events_management.service;

import com.riwi.events_management.dto.VenueDTO;
import com.riwi.events_management.repository.VenueRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VenueService {
    private final VenueRepository repository;

    public VenueService(VenueRepository repository) {
        this.repository = repository;
    }

    public List<VenueDTO> getAll() {
        return repository.findAll();
    }

    public Optional<VenueDTO> getById(Long id) {
        return repository.findById(id);
    }

    public VenueDTO create(VenueDTO venue) {
        return repository.save(venue);
    }

    public boolean delete(Long id) {
        return repository.delete(id);
    }
}


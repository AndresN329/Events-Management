package com.riwi.events_management.service;

import com.riwi.events_management.exception.ResourceNotFoundException;
import com.riwi.events_management.repository.VenueRepository;
import com.riwi.ticketup.dto.VenueDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueService {

    private final VenueRepository repository;

    public List<VenueDTO> getAll() {
        return repository.findAll();
    }

    public VenueDTO getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("El venue (lugar) con ID " + id + " no existe"));
    }

    public VenueDTO create(VenueDTO venue) {
        return repository.save(venue);
    }

    public void delete(Long id) {
        boolean deleted = repository.delete(id);

        if (!deleted) {
            throw new ResourceNotFoundException("No se pudo eliminar. El venue con ID " + id + " no existe");
        }
    }
}
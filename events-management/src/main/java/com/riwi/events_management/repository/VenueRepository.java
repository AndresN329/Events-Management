package com.riwi.events_management.repository;

import com.riwi.events_management.dto.VenueDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class VenueRepository {
    private final List<VenueDTO> venues = new ArrayList<>();
    private Long nextId = 1L;

    public List<VenueDTO> findAll() {
        return venues;
    }

    public Optional<VenueDTO> findById(Long id) {
        return venues.stream().filter(v -> v.getId().equals(id)).findFirst();
    }

    public VenueDTO save(VenueDTO venue) {
        if (venue.getId() == null) {
            venue.setId(nextId++);
        }
        venues.add(venue);
        return venue;
    }

    public boolean delete(Long id) {
        return venues.removeIf(v -> v.getId().equals(id));
    }
}

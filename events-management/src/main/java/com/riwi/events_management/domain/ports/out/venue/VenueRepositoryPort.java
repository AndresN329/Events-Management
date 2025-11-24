package com.riwi.events_management.domain.ports.out.venue;

import com.riwi.events_management.domain.model.Venue;
import java.util.List;
import java.util.Optional;

public interface VenueRepositoryPort {

    Venue save(Venue venue);

    Optional<Venue> findById(Long id);

    List<Venue> findAll();

    void deleteById(Long id);
}

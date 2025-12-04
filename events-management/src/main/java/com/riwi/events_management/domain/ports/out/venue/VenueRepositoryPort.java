package com.riwi.events_management.domain.ports.out.venue;

import com.riwi.events_management.domain.model.Venue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface VenueRepositoryPort {

    Venue save(Venue venue);

    Optional<Venue> findById(Long id);

    void deleteById(Long id);

    Page<Venue> findAllWithFilters(
            String name,
            String address,
            Integer minCapacity,
            Integer maxCapacity,
            Pageable pageable
    );
}

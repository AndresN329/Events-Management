package com.riwi.events_management.domain.ports.out.event;

import com.riwi.events_management.domain.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Optional;
import java.time.LocalDateTime;

public interface EventRepositoryPort {

    Event save(Event event);

    Optional<Event> findById(Long id);

    void deleteById(Long id);

    // HU4 Filters + Pagination
    Page<Event> findAllWithFilters(
            Long venueId,
            String category,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Pageable pageable
    );

    boolean existsById(Long id);
}

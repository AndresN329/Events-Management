package com.riwi.events_management.domain.ports.in.event;

import com.riwi.events_management.domain.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface GetFilteredEventsUseCase {

    Page<Event> findFiltered(
            Long venueId,
            String category,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Pageable pageable
    );
}
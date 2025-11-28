package com.riwi.events_management.domain.ports.in.event;

import com.riwi.events_management.domain.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface GetFilteredEventsUseCase {

    Page<Event> findFiltered(
            Long venueId,
            String category,
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable
    );
}

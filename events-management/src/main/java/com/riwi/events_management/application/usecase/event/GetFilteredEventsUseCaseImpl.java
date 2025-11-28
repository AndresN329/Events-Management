package com.riwi.events_management.application.usecase.event;

import com.riwi.events_management.domain.model.Event;
import com.riwi.events_management.domain.ports.in.event.GetFilteredEventsUseCase;
import com.riwi.events_management.domain.ports.out.event.EventRepositoryPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional(readOnly = true)
public class GetFilteredEventsUseCaseImpl implements GetFilteredEventsUseCase {

    private final EventRepositoryPort repository;

    public GetFilteredEventsUseCaseImpl(EventRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Page<Event> findFiltered(
            Long venueId,
            String category,
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable
    ) {
        return repository.findAllWithFilters(
                venueId,
                category,
                startDate,
                endDate,
                pageable
        );
    }
}

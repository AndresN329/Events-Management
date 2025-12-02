package com.riwi.events_management.application.usecase.event;

import com.riwi.events_management.domain.model.Event;
import com.riwi.events_management.domain.ports.in.event.GetFilteredEventsUseCase;
import com.riwi.events_management.domain.ports.out.event.EventRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
public class GetFilteredEventsUseCaseImpl implements GetFilteredEventsUseCase {

    private static final Logger log = LoggerFactory.getLogger(GetFilteredEventsUseCaseImpl.class);

    private final EventRepositoryPort repository;

    public GetFilteredEventsUseCaseImpl(EventRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Page<Event> findFiltered(
            Long venueId,
            String category,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Pageable pageable
    ) {
        log.info(
                "🔍 [USECASE] Filtrando eventos: venueId={}, category={}, startDate={}, endDate={}, page={}, size={}",
                venueId,
                category,
                startDate,
                endDate,
                pageable.getPageNumber(),
                pageable.getPageSize()
        );

        Page<Event> result = repository.findAllWithFilters(
                venueId,
                category,
                startDate,
                endDate,
                pageable
        );

        log.info("📄 [USECASE] Eventos encontrados: {}", result.getTotalElements());

        return result;
    }
}

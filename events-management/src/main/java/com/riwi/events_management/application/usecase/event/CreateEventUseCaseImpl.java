package com.riwi.events_management.application.usecase.event;

import com.riwi.events_management.domain.model.Event;
import com.riwi.events_management.domain.ports.in.event.CreateEventUseCase;
import com.riwi.events_management.domain.ports.out.event.EventRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreateEventUseCaseImpl implements CreateEventUseCase {

    private static final Logger log = LoggerFactory.getLogger(CreateEventUseCaseImpl.class);

    private final EventRepositoryPort repository;

    public CreateEventUseCaseImpl(EventRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Event create(Event event) {
        log.info("➜ [USECASE] Creando evento: name={}, category={}, venueId={}",
                event.getName(), event.getCategory(), event.getVenueId());

        Event saved = repository.save(event);

        log.info("✔ [USECASE] Evento creado con ID: {}", saved.getId());
        return saved;
    }
}

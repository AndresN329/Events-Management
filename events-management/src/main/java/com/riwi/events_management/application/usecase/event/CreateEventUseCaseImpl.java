package com.riwi.events_management.application.usecase.event;

import com.riwi.events_management.domain.model.Event;
import com.riwi.events_management.domain.ports.in.event.CreateEventUseCase;
import com.riwi.events_management.domain.ports.out.event.EventRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class CreateEventUseCaseImpl implements CreateEventUseCase {

    private final EventRepositoryPort repository;

    public CreateEventUseCaseImpl(EventRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Event create(Event event) {
        return repository.save(event);
    }
}
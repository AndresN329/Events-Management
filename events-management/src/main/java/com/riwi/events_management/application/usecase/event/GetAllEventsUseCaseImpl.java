package com.riwi.events_management.application.usecase.event;

import com.riwi.events_management.domain.model.Event;
import com.riwi.events_management.domain.ports.in.event.GetAllEventsUseCase;
import com.riwi.events_management.domain.ports.out.event.EventRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllEventsUseCaseImpl implements GetAllEventsUseCase {

    private final EventRepositoryPort repository;

    public GetAllEventsUseCaseImpl(EventRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<Event> findAll() {
        return repository.findAll();
    }
}

package com.riwi.events_management.application.usecase.event;

import com.riwi.events_management.domain.model.Event;
import com.riwi.events_management.domain.ports.in.event.GetEventUseCaseById;
import com.riwi.events_management.domain.ports.out.event.EventRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class GetEventUseCaseImpl implements GetEventUseCaseById {

    private final EventRepositoryPort repository;

    public GetEventUseCaseImpl(EventRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Event> findById(Long id) {
        return repository.findById(id);
    }
}


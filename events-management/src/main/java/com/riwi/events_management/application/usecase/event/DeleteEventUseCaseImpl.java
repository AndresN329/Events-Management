package com.riwi.events_management.application.usecase.event;

import com.riwi.events_management.domain.ports.in.event.DeleteEventUseCase;
import com.riwi.events_management.domain.ports.out.event.EventRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class DeleteEventUseCaseImpl implements DeleteEventUseCase {

    private final EventRepositoryPort repository;

    public DeleteEventUseCaseImpl(EventRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

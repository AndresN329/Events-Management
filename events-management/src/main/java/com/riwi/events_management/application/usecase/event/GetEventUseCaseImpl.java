package com.riwi.events_management.application.usecase.event;

import com.riwi.events_management.domain.model.Event;
import com.riwi.events_management.domain.ports.in.event.GetEventUseCaseById;
import com.riwi.events_management.domain.ports.out.event.EventRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class GetEventUseCaseImpl implements GetEventUseCaseById {

    private static final Logger log = LoggerFactory.getLogger(GetEventUseCaseImpl.class);

    private final EventRepositoryPort repository;

    public GetEventUseCaseImpl(EventRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Event> findById(Long id) {

        log.info("🔍 [USECASE] Buscando evento con ID {}", id);

        Optional<Event> result = repository.findById(id);

        if (result.isPresent()) {
            log.info("✔ [USECASE] Evento encontrado con ID {}", id);
        } else {
            log.warn("⚠ [USECASE] No se encontró evento con ID {}", id);
        }

        return result;
    }
}

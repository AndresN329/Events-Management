package com.riwi.events_management.application.usecase.event;

import com.riwi.events_management.domain.ports.in.event.DeleteEventUseCase;
import com.riwi.events_management.domain.ports.out.event.EventRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeleteEventUseCaseImpl implements DeleteEventUseCase {

    private static final Logger log = LoggerFactory.getLogger(DeleteEventUseCaseImpl.class);

    private final EventRepositoryPort repository;

    public DeleteEventUseCaseImpl(EventRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public void deleteById(Long id) {

        log.warn("⛔ [USECASE] Intentando eliminar evento con ID {}", id);

        if (!repository.existsById(id)) {
            log.error("❌ [USECASE] No se pudo eliminar: el evento con ID {} no existe", id);
            throw new IllegalArgumentException("El evento con ID " + id + " no existe");
        }

        repository.deleteById(id);

        log.info("✔ [USECASE] Evento con ID {} eliminado exitosamente", id);
    }
}

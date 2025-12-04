package com.riwi.events_management.application.usecase.venue;

import com.riwi.events_management.domain.ports.in.venue.DeleteVenueUseCase;
import com.riwi.events_management.domain.ports.out.venue.VenueRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional // escritura → requiere transacción
public class DeleteVenueUseCaseImpl implements DeleteVenueUseCase {

    private static final Logger log = LoggerFactory.getLogger(DeleteVenueUseCaseImpl.class);

    private final VenueRepositoryPort repository;

    public DeleteVenueUseCaseImpl(VenueRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public void deleteById(Long id) {

        log.warn("🗑️ [USECASE] Eliminando venue con ID {}", id);

        repository.deleteById(id);

        log.info("✅ [USECASE] Venue eliminado con ID {}", id);
    }
}

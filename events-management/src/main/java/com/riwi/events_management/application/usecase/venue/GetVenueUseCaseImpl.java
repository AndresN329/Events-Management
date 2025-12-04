package com.riwi.events_management.application.usecase.venue;

import com.riwi.events_management.domain.model.Venue;
import com.riwi.events_management.domain.ports.in.venue.GetVenueUseCaseById;
import com.riwi.events_management.domain.ports.out.venue.VenueRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)  // Lectura → transacción ligera
public class GetVenueUseCaseImpl implements GetVenueUseCaseById {

    private static final Logger log = LoggerFactory.getLogger(GetVenueUseCaseImpl.class);

    private final VenueRepositoryPort repository;

    public GetVenueUseCaseImpl(VenueRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Venue> findById(Long id) {

        log.info("🔍 [USECASE] Buscando venue por ID: {}", id);

        Optional<Venue> venue = repository.findById(id);

        if (venue.isPresent()) {
            log.info("✅ [USECASE] Venue encontrado - ID: {}", id);
        } else {
            log.warn("⚠️ [USECASE] Venue NO encontrado - ID: {}", id);
        }

        return venue;
    }
}

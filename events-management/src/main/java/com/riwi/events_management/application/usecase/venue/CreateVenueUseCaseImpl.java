package com.riwi.events_management.application.usecase.venue;

import com.riwi.events_management.domain.model.Venue;
import com.riwi.events_management.domain.ports.in.venue.CreateVenueUseCase;
import com.riwi.events_management.domain.ports.out.venue.VenueRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional // Escritura: transacción completa
public class CreateVenueUseCaseImpl implements CreateVenueUseCase {

    private static final Logger log = LoggerFactory.getLogger(CreateVenueUseCaseImpl.class);

    private final VenueRepositoryPort repository;

    public CreateVenueUseCaseImpl(VenueRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Venue create(Venue venue) {

        log.info("🏗️ [USECASE] Creando venue: name={}, address={}, minCap={}, maxCap={}",
                venue.getName(),
                venue.getAddress(),
                venue.getMinCapacity(),
                venue.getMaxCapacity()
        );

        Venue saved = repository.save(venue);

        log.info("✅ [USECASE] Venue creado con ID: {}", saved.getId());

        return saved;
    }
}

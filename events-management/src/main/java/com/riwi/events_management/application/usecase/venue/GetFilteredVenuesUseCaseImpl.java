package com.riwi.events_management.application.usecase.venue;

import com.riwi.events_management.domain.model.Venue;
import com.riwi.events_management.domain.ports.in.venue.GetFilteredVenuesUseCase;
import com.riwi.events_management.domain.ports.out.venue.VenueRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class GetFilteredVenuesUseCaseImpl implements GetFilteredVenuesUseCase {

    private static final Logger log = LoggerFactory.getLogger(GetFilteredVenuesUseCaseImpl.class);

    private final VenueRepositoryPort repository;

    public GetFilteredVenuesUseCaseImpl(VenueRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Page<Venue> findAllWithFilters(
            String name,
            String address,
            Integer minCapacity,
            Integer maxCapacity,
            Pageable pageable
    ) {

        log.info("🔍 [USECASE] Filtrando venues - name={}, address={}, minCap={}, maxCap={}, page={}",
                name, address, minCapacity, maxCapacity, pageable);

        Page<Venue> venues = repository.findAllWithFilters(
                name,
                address,
                minCapacity,
                maxCapacity,
                pageable
        );

        log.info("📄 [USECASE] Venues encontrados: {}", venues.getTotalElements());

        return venues;
    }
}

package com.riwi.events_management.application.usecase.venue;

import com.riwi.events_management.domain.model.Venue;
import com.riwi.events_management.domain.ports.in.venue.GetFilteredVenuesUseCase;
import com.riwi.events_management.domain.ports.out.venue.VenueRepositoryPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class GetFilteredVenuesUseCaseImpl implements GetFilteredVenuesUseCase {

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
        return repository.findAllWithFilters(
                name,
                address,
                minCapacity,
                maxCapacity,
                pageable
        );
    }
}

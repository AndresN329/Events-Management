package com.riwi.events_management.application.usecase.venue;

import com.riwi.events_management.domain.model.Venue;
import com.riwi.events_management.domain.ports.in.venue.GetAllVenuesUseCase;
import com.riwi.events_management.domain.ports.out.venue.VenueRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllVenuesUseCaseImpl implements GetAllVenuesUseCase {

    private final VenueRepositoryPort repository;

    public GetAllVenuesUseCaseImpl(VenueRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<Venue> findAll() {
        return repository.findAll();
    }
}

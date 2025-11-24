package com.riwi.events_management.application.usecase.venue;


import com.riwi.events_management.domain.model.Venue;
import com.riwi.events_management.domain.ports.in.venue.CreateVenueUseCase;
import com.riwi.events_management.domain.ports.out.venue.VenueRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class CreateVenueUseCaseImpl implements CreateVenueUseCase {

    private final VenueRepositoryPort repository;

    public CreateVenueUseCaseImpl(VenueRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Venue create(Venue venue) {
        return repository.save(venue);
    }
}

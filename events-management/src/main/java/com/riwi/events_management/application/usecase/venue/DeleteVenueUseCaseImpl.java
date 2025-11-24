package com.riwi.events_management.application.usecase.venue;

import com.riwi.events_management.domain.ports.in.venue.DeleteVenueUseCase;
import com.riwi.events_management.domain.ports.out.venue.VenueRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class DeleteVenueUseCaseImpl implements DeleteVenueUseCase {

    private final VenueRepositoryPort repository;

    public DeleteVenueUseCaseImpl(VenueRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

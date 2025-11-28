package com.riwi.events_management.application.usecase.venue;

import com.riwi.events_management.domain.model.Venue;
import com.riwi.events_management.domain.ports.in.venue.GetVenueUseCaseById;
import com.riwi.events_management.domain.ports.out.venue.VenueRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class GetVenueUseCaseImpl implements GetVenueUseCaseById {

    private final VenueRepositoryPort repository;

    public GetVenueUseCaseImpl(VenueRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Venue> findById(Long id) {
        return repository.findById(id);
    }
}

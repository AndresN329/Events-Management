package com.riwi.events_management.domain.ports.in.venue;

import com.riwi.events_management.domain.model.Venue;

import java.util.Optional;

public interface GetVenueUseCaseById {
    Optional<Venue> findById(Long id);
}


package com.riwi.events_management.domain.ports.in.venue;

import com.riwi.events_management.domain.model.Venue;

public interface CreateVenueUseCase {
    Venue create(Venue venue);
}


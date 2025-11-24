package com.riwi.events_management.domain.ports.in.venue;

import com.riwi.events_management.domain.model.Venue;

import java.util.List;

public interface GetAllVenuesUseCase {
    List<Venue> findAll();
}

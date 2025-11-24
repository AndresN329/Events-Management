package com.riwi.events_management.domain.ports.in.event;

import com.riwi.events_management.domain.model.Event;

import java.util.Optional;

public interface GetEventUseCaseById {
    Optional<Event> findById(Long id);
}

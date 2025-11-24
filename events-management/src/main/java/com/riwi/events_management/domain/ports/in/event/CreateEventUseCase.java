package com.riwi.events_management.domain.ports.in.event;

import com.riwi.events_management.domain.model.Event;

public interface CreateEventUseCase {
    Event create(Event event);
}

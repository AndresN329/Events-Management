package com.riwi.events_management.domain.ports.in.event;

import com.riwi.events_management.domain.model.Event;

import java.util.List;

public interface GetAllEventsUseCase {
    List<Event> findAll();
}

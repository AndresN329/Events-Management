package com.riwi.events_management.domain.ports.out.event;

import com.riwi.events_management.domain.model.Event;
import java.util.Optional;
import java.util.List;

public interface EventRepositoryPort {

    Event save(Event event);

    Optional<Event> findById(Long id);

    List<Event> findAll();

    void deleteById(Long id);
}

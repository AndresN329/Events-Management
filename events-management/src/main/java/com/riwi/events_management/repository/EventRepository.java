package com.riwi.events_management.repository;

import com.riwi.events_management.dto.EventDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EventRepository {
    private final List<EventDTO> events = new ArrayList<>();
    private Long nextId = 1L;

    public List<EventDTO> findAll() {
        return events;
    }

    public Optional<EventDTO> findById(Long id) {
        return events.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    public EventDTO save(EventDTO event) {
        if (event.getId() == null) {
            event.setId(nextId++);
        }
        events.add(event);
        return event;
    }

    public boolean delete(Long id) {
        return events.removeIf(e -> e.getId().equals(id));
    }
}

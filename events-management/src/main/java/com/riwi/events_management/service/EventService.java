package com.riwi.events_management.service;


import com.riwi.events_management.dto.EventDTO;
import com.riwi.events_management.repository.EventRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public List<EventDTO> getAll() {
        return repository.findAll();
    }

    public Optional<EventDTO> getById(Long id) {
        return repository.findById(id);
    }

    public EventDTO create(EventDTO event) {
        return repository.save(event);
    }

    public boolean delete(Long id) {
        return repository.delete(id);
    }
}

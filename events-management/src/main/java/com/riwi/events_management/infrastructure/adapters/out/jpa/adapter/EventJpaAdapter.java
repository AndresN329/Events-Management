package com.riwi.events_management.infrastructure.adapters.out.jpa.adapter;

import com.riwi.events_management.domain.model.Event;
import com.riwi.events_management.domain.ports.out.event.EventRepositoryPort;
import com.riwi.events_management.infrastructure.adapters.out.jpa.entity.EventJpaEntity;
import com.riwi.events_management.infrastructure.adapters.out.jpa.repository.SpringDataEventRepository;
import com.riwi.events_management.infrastructure.mapper.EventMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EventJpaAdapter implements EventRepositoryPort {

    private final SpringDataEventRepository repository;
    private final EventMapper mapper;

    public EventJpaAdapter(SpringDataEventRepository repository, EventMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Event save(Event event) {
        EventJpaEntity entity = mapper.toJpaEntity(event);
        EventJpaEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Event> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Event> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

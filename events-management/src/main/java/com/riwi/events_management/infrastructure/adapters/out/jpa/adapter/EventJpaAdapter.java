package com.riwi.events_management.infrastructure.adapters.out.jpa.adapter;

import com.riwi.events_management.domain.model.Event;
import com.riwi.events_management.domain.ports.out.event.EventRepositoryPort;
import com.riwi.events_management.infrastructure.adapters.out.jpa.entity.EventJpaEntity;
import com.riwi.events_management.infrastructure.adapters.out.jpa.repository.SpringDataEventRepository;
import com.riwi.events_management.infrastructure.adapters.out.jpa.specification.EventSpecifications;
import com.riwi.events_management.infrastructure.mapper.EventMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
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
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<Event> findAllWithFilters(
            Long venueId,
            String category,
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable
    ) {

        Specification<EventJpaEntity> spec =
                EventSpecifications.byVenue(venueId)
                        .and(EventSpecifications.byCategory(category))
                        .and(EventSpecifications.byDateRange(startDate, endDate));

        return repository.findAll(spec, pageable)
                .map(mapper::toDomain);
    }
}

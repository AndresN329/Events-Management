package com.riwi.events_management.infrastructure.adapters.out.jpa.adapter;

import com.riwi.events_management.domain.model.Venue;
import com.riwi.events_management.domain.ports.out.venue.VenueRepositoryPort;
import com.riwi.events_management.infrastructure.adapters.out.jpa.entity.VenueJpaEntity;
import com.riwi.events_management.infrastructure.adapters.out.jpa.repository.SpringDataVenueRepository;
import com.riwi.events_management.infrastructure.adapters.out.jpa.specification.VenueSpecifications;
import com.riwi.events_management.infrastructure.mapper.VenueMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class VenueJpaAdapter implements VenueRepositoryPort {

    private final SpringDataVenueRepository repository;
    private final VenueMapper mapper;

    public VenueJpaAdapter(SpringDataVenueRepository repository, VenueMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Venue save(Venue venue) {
        VenueJpaEntity entity = mapper.toJpaEntity(venue);
        VenueJpaEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Venue> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<Venue> findAllWithFilters(
            String name,
            String address,
            Integer minCapacity,
            Integer maxCapacity,
            Pageable pageable
    ) {

        Specification<VenueJpaEntity> spec =
                VenueSpecifications.byName(name)
                        .and(VenueSpecifications.byAddress(address))
                        .and(VenueSpecifications.minCapacity(minCapacity))
                        .and(VenueSpecifications.maxCapacity(maxCapacity));

        return repository.findAll(spec, pageable)
                .map(mapper::toDomain);
    }
}

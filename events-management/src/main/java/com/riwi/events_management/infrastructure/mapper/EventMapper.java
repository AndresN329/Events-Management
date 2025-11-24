package com.riwi.events_management.infrastructure.mapper;

import com.riwi.events_management.domain.model.Event;
import com.riwi.events_management.infrastructure.adapters.out.jpa.entity.EventJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapper {

    // Domain → JPA
    @Mapping(source = "venueId", target = "venue.id")
    EventJpaEntity toJpaEntity(Event domain);

    // JPA → Domain
    @Mapping(source = "venue.id", target = "venueId")
    Event toDomain(EventJpaEntity entity);
}

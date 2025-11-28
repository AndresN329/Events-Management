package com.riwi.events_management.infrastructure.mapper;

import com.riwi.events_management.domain.model.Venue;
import com.riwi.events_management.infrastructure.adapters.out.jpa.entity.VenueJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VenueMapper {

    // Domain → JPA (no mapeamos la lista de eventos)
    @Mapping(target = "events", ignore = true)
    VenueJpaEntity toJpaEntity(Venue domain);

    // JPA → Domain (NO ignoramos nada porque Venue no tiene 'events')
    Venue toDomain(VenueJpaEntity entity);
}

package com.riwi.events_management.infrastructure.mapper;

import com.riwi.events_management.domain.model.Venue;
import com.riwi.events_management.infrastructure.adapters.out.jpa.entity.VenueJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VenueMapper {

    @Mapping(target = "events", ignore = true)
    VenueJpaEntity toJpaEntity(Venue domain);

    Venue toDomain(VenueJpaEntity entity);
}

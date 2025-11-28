package com.riwi.events_management.infrastructure.mapper;

import com.riwi.events_management.domain.model.Event;
import com.riwi.events_management.infrastructure.adapters.out.jpa.entity.EventJpaEntity;
import com.riwi.events_management.infrastructure.adapters.out.jpa.entity.VenueJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapper {

    // Domain → JPA
    @Mapping(source = "venueId", target = "venue")
    EventJpaEntity toJpaEntity(Event domain);

    // JPA → Domain
    @Mapping(source = "venue.id", target = "venueId")
    Event toDomain(EventJpaEntity entity);

    // Método auxiliar — MapStruct lo usa automáticamente
    default VenueJpaEntity mapVenue(Long venueId) {
        if (venueId == null) return null;
        VenueJpaEntity venue = new VenueJpaEntity();
        venue.setId(venueId);
        return venue;
    }
}

package com.riwi.events_management.infrastructure.mapper;

import com.riwi.events_management.application.dto.request.EventRequest;
import com.riwi.events_management.application.dto.response.EventResponse;
import com.riwi.events_management.domain.model.Event;
import com.riwi.events_management.infrastructure.adapters.out.jpa.entity.EventJpaEntity;
import com.riwi.events_management.infrastructure.adapters.out.jpa.entity.VenueJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface EventMapper {

    // ==========================
    // DTO → DOMAIN
    // ==========================
    Event toDomain(EventRequest request);

    // ==========================
    // DOMAIN → RESPONSE DTO
    // ==========================
    EventResponse toResponse(Event domain);

    // ==========================
    // DOMAIN → JPA ENTITY
    // ==========================
    @Mapping(source = "venueId", target = "venue", qualifiedByName = "mapVenue")
    @Mapping(target = "id", ignore = true)
    EventJpaEntity toJpaEntity(Event domain);

    // ==========================
    // JPA ENTITY → DOMAIN
    // ==========================
    @Mapping(source = "venue.id", target = "venueId")
    Event toDomain(EventJpaEntity entity);

    // ==========================
    // HELPER (reconocido por MapStruct)
    // ==========================
    @Named("mapVenue")
    default VenueJpaEntity mapVenue(Long venueId) {
        if (venueId == null) return null;
        VenueJpaEntity venue = new VenueJpaEntity();
        venue.setId(venueId);
        return venue;
    }
}
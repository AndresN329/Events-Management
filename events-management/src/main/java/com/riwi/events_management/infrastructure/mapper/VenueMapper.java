package com.riwi.events_management.infrastructure.mapper;

import com.riwi.events_management.application.dto.request.VenueRequest;
import com.riwi.events_management.application.dto.response.VenueResponse;
import com.riwi.events_management.domain.model.Venue;
import com.riwi.events_management.infrastructure.adapters.out.jpa.entity.VenueJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VenueMapper {

    // ===============================
    // DTO REQUEST → DOMAIN
    // ===============================
    Venue toDomain(VenueRequest request);

    // ===============================
    // DOMAIN → DTO RESPONSE
    // ===============================
    VenueResponse toResponse(Venue domain);

    // ===============================
    // DOMAIN → JPA
    // ===============================
    @Mapping(target = "events", ignore = true)
    VenueJpaEntity toJpaEntity(Venue domain);

    // ===============================
    // JPA → DOMAIN
    // ===============================
    Venue toDomain(VenueJpaEntity entity);
}

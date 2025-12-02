package com.riwi.events_management.infrastructure.mapper;

import com.riwi.events_management.application.dto.request.VenueRequest;
import com.riwi.events_management.application.dto.response.VenueResponse;
import com.riwi.events_management.domain.model.Venue;
import com.riwi.events_management.infrastructure.adapters.out.jpa.entity.VenueJpaEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-02T07:42:20-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class VenueMapperImpl implements VenueMapper {

    @Override
    public Venue toDomain(VenueRequest request) {
        if ( request == null ) {
            return null;
        }

        Venue venue = new Venue();

        venue.setName( request.getName() );
        venue.setAddress( request.getAddress() );
        venue.setMinCapacity( request.getMinCapacity() );
        venue.setMaxCapacity( request.getMaxCapacity() );
        venue.setDescription( request.getDescription() );

        return venue;
    }

    @Override
    public VenueResponse toResponse(Venue domain) {
        if ( domain == null ) {
            return null;
        }

        VenueResponse venueResponse = new VenueResponse();

        venueResponse.setId( domain.getId() );
        venueResponse.setName( domain.getName() );
        venueResponse.setAddress( domain.getAddress() );
        venueResponse.setMinCapacity( domain.getMinCapacity() );
        venueResponse.setMaxCapacity( domain.getMaxCapacity() );
        venueResponse.setDescription( domain.getDescription() );

        return venueResponse;
    }

    @Override
    public VenueJpaEntity toJpaEntity(Venue domain) {
        if ( domain == null ) {
            return null;
        }

        VenueJpaEntity.VenueJpaEntityBuilder venueJpaEntity = VenueJpaEntity.builder();

        venueJpaEntity.id( domain.getId() );
        venueJpaEntity.name( domain.getName() );
        venueJpaEntity.address( domain.getAddress() );
        venueJpaEntity.minCapacity( domain.getMinCapacity() );
        venueJpaEntity.maxCapacity( domain.getMaxCapacity() );
        venueJpaEntity.description( domain.getDescription() );

        return venueJpaEntity.build();
    }

    @Override
    public Venue toDomain(VenueJpaEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Venue venue = new Venue();

        venue.setId( entity.getId() );
        venue.setName( entity.getName() );
        venue.setAddress( entity.getAddress() );
        venue.setMinCapacity( entity.getMinCapacity() );
        venue.setMaxCapacity( entity.getMaxCapacity() );
        venue.setDescription( entity.getDescription() );

        return venue;
    }
}

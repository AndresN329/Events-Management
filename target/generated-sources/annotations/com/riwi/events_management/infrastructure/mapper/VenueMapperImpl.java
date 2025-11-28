package com.riwi.events_management.infrastructure.mapper;

import com.riwi.events_management.domain.model.Venue;
import com.riwi.events_management.infrastructure.adapters.out.jpa.entity.VenueJpaEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-27T21:53:54-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class VenueMapperImpl implements VenueMapper {

    @Override
    public VenueJpaEntity toJpaEntity(Venue domain) {
        if ( domain == null ) {
            return null;
        }

        VenueJpaEntity.VenueJpaEntityBuilder venueJpaEntity = VenueJpaEntity.builder();

        venueJpaEntity.id( domain.getId() );
        venueJpaEntity.name( domain.getName() );
        venueJpaEntity.capacity( domain.getCapacity() );
        venueJpaEntity.address( domain.getAddress() );

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
        venue.setCapacity( entity.getCapacity() );
        venue.setAddress( entity.getAddress() );

        return venue;
    }
}

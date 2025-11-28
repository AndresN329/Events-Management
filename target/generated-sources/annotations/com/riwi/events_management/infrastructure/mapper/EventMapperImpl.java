package com.riwi.events_management.infrastructure.mapper;

import com.riwi.events_management.domain.model.Event;
import com.riwi.events_management.infrastructure.adapters.out.jpa.entity.EventJpaEntity;
import com.riwi.events_management.infrastructure.adapters.out.jpa.entity.VenueJpaEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-27T21:53:54-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class EventMapperImpl implements EventMapper {

    @Override
    public EventJpaEntity toJpaEntity(Event domain) {
        if ( domain == null ) {
            return null;
        }

        EventJpaEntity.EventJpaEntityBuilder eventJpaEntity = EventJpaEntity.builder();

        eventJpaEntity.venue( mapVenue( domain.getVenueId() ) );
        eventJpaEntity.id( domain.getId() );
        eventJpaEntity.name( domain.getName() );
        eventJpaEntity.description( domain.getDescription() );
        eventJpaEntity.category( domain.getCategory() );
        eventJpaEntity.city( domain.getCity() );
        eventJpaEntity.startDate( domain.getStartDate() );
        eventJpaEntity.capacity( domain.getCapacity() );

        return eventJpaEntity.build();
    }

    @Override
    public Event toDomain(EventJpaEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Event event = new Event();

        event.setVenueId( entityVenueId( entity ) );
        event.setId( entity.getId() );
        event.setName( entity.getName() );
        event.setDescription( entity.getDescription() );
        event.setCategory( entity.getCategory() );
        event.setCity( entity.getCity() );
        event.setStartDate( entity.getStartDate() );
        event.setCapacity( entity.getCapacity() );

        return event;
    }

    private Long entityVenueId(EventJpaEntity eventJpaEntity) {
        if ( eventJpaEntity == null ) {
            return null;
        }
        VenueJpaEntity venue = eventJpaEntity.getVenue();
        if ( venue == null ) {
            return null;
        }
        Long id = venue.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}

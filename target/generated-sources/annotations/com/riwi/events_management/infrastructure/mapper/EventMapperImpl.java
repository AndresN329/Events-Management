package com.riwi.events_management.infrastructure.mapper;

import com.riwi.events_management.application.dto.request.EventRequest;
import com.riwi.events_management.application.dto.response.EventResponse;
import com.riwi.events_management.domain.model.Event;
import com.riwi.events_management.infrastructure.adapters.out.jpa.entity.EventJpaEntity;
import com.riwi.events_management.infrastructure.adapters.out.jpa.entity.VenueJpaEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-02T07:42:21-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class EventMapperImpl implements EventMapper {

    @Override
    public Event toDomain(EventRequest request) {
        if ( request == null ) {
            return null;
        }

        Event event = new Event();

        event.setName( request.getName() );
        event.setDescription( request.getDescription() );
        event.setCategory( request.getCategory() );
        event.setStartDate( request.getStartDate() );
        event.setEndDate( request.getEndDate() );
        event.setCapacity( request.getCapacity() );
        event.setVenueId( request.getVenueId() );

        return event;
    }

    @Override
    public EventResponse toResponse(Event domain) {
        if ( domain == null ) {
            return null;
        }

        EventResponse eventResponse = new EventResponse();

        eventResponse.setId( domain.getId() );
        eventResponse.setName( domain.getName() );
        eventResponse.setCategory( domain.getCategory() );
        eventResponse.setStartDate( domain.getStartDate() );
        eventResponse.setEndDate( domain.getEndDate() );
        eventResponse.setVenueId( domain.getVenueId() );
        eventResponse.setCapacity( domain.getCapacity() );
        eventResponse.setDescription( domain.getDescription() );

        return eventResponse;
    }

    @Override
    public EventJpaEntity toJpaEntity(Event domain) {
        if ( domain == null ) {
            return null;
        }

        EventJpaEntity.EventJpaEntityBuilder eventJpaEntity = EventJpaEntity.builder();

        eventJpaEntity.venue( mapVenue( domain.getVenueId() ) );
        eventJpaEntity.name( domain.getName() );
        eventJpaEntity.description( domain.getDescription() );
        eventJpaEntity.category( domain.getCategory() );
        eventJpaEntity.startDate( domain.getStartDate() );
        eventJpaEntity.endDate( domain.getEndDate() );
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
        event.setStartDate( entity.getStartDate() );
        event.setEndDate( entity.getEndDate() );
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

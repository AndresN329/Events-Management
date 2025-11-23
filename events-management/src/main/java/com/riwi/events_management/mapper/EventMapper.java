package com.riwi.events_management.mapper;

import com.riwi.events_management.dto.EventDTO;
import com.riwi.events_management.entity.EventEntity;
import com.riwi.events_management.entity.VenueEntity;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    public EventDTO toDTO(EventEntity entity) {
        EventDTO dto = new EventDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setCategory(entity.getCategory());
        dto.setCity(entity.getCity());
        dto.setStartDate(entity.getStartDate());
        dto.setCapacity(entity.getCapacity());
        dto.setVenueId(entity.getVenue().getId());
        return dto;
    }

    public EventEntity toEntity(EventDTO dto, VenueEntity venue) {
        return EventEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .category(dto.getCategory())
                .city(dto.getCity())
                .startDate(dto.getStartDate())
                .capacity(dto.getCapacity())
                .venue(venue)
                .build();
    }
}

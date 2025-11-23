package com.riwi.events_management.mapper;

import com.riwi.events_management.dto.VenueDTO;
import com.riwi.events_management.entity.VenueEntity;
import org.springframework.stereotype.Component;

@Component
public class VenueMapper {

    public VenueDTO toDTO(VenueEntity entity) {
        VenueDTO dto = new VenueDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCapacity(entity.getCapacity());
        dto.setAddress(entity.getAddress());
        return dto;
    }

    public VenueEntity toEntity(VenueDTO dto) {
        return VenueEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .capacity(dto.getCapacity())
                .address(dto.getAddress())
                .build();
    }
}

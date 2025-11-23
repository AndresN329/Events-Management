package com.riwi.events_management.service;

import com.riwi.events_management.dto.VenueDTO;

import java.util.List;

public interface VenueService {
    VenueDTO create(VenueDTO dto);
    VenueDTO update(Long id, VenueDTO dto);
    VenueDTO findById(Long id);
    List<VenueDTO> findAll();
    void delete(Long id);
}

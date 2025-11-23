package com.riwi.events_management.service;

import com.riwi.events_management.dto.EventDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface EventService {

    EventDTO create(EventDTO dto);

    EventDTO update(Long id, EventDTO dto);

    EventDTO findById(Long id);

    Page<EventDTO> findAll(Pageable pageable);

    Page<EventDTO> filter(String city, String category, LocalDate startDate, Pageable pageable);

    void delete(Long id);
}

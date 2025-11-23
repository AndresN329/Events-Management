package com.riwi.events_management.service.impl;

import com.riwi.events_management.dto.EventDTO;
import com.riwi.events_management.entity.EventEntity;
import com.riwi.events_management.entity.VenueEntity;
import com.riwi.events_management.exception.ConflictException;
import com.riwi.events_management.exception.NotFoundException;
import com.riwi.events_management.mapper.EventMapper;
import com.riwi.events_management.repository.EventRepository;
import com.riwi.events_management.repository.VenueRepository;
import com.riwi.events_management.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;
    private final EventMapper mapper;

    @Override
    public EventDTO create(EventDTO dto) {

        if (eventRepository.existsByName(dto.getName())) {
            throw new ConflictException("El nombre del evento ya existe");
        }

        VenueEntity venue = venueRepository.findById(dto.getVenueId())
                .orElseThrow(() -> new NotFoundException("El venue no existe"));

        EventEntity entity = mapper.toEntity(dto, venue);
        return mapper.toDTO(eventRepository.save(entity));
    }

    @Override
    public EventDTO update(Long id, EventDTO dto) {
        EventEntity event = eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Evento no encontrado"));

        if (!event.getName().equals(dto.getName()) &&
                eventRepository.existsByName(dto.getName())) {
            throw new ConflictException("Ya existe otro evento con ese nombre");
        }

        VenueEntity venue = venueRepository.findById(dto.getVenueId())
                .orElseThrow(() -> new NotFoundException("Venue no encontrado"));

        EventEntity updated = mapper.toEntity(dto, venue);
        updated.setId(id);

        return mapper.toDTO(eventRepository.save(updated));
    }

    @Override
    public EventDTO findById(Long id) {
        return mapper.toDTO(
                eventRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Evento no encontrado"))
        );
    }

    @Override
    public Page<EventDTO> findAll(Pageable pageable) {
        return eventRepository.findAll(pageable).map(mapper::toDTO);
    }

    @Override
    public Page<EventDTO> filter(String city, String category, LocalDate startDate, Pageable pageable) {
        return eventRepository.filter(city, category, startDate, pageable)
                .map(mapper::toDTO);
    }

    @Override
    public void delete(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new NotFoundException("Evento no encontrado");
        }
        eventRepository.deleteById(id);
    }
}

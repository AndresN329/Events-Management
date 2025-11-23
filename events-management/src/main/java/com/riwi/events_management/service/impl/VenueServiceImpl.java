package com.riwi.events_management.service.impl;

import com.riwi.events_management.dto.VenueDTO;
import com.riwi.events_management.entity.VenueEntity;
import com.riwi.events_management.exception.NotFoundException;
import com.riwi.events_management.mapper.VenueMapper;
import com.riwi.events_management.repository.VenueRepository;
import com.riwi.events_management.service.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueServiceImpl implements VenueService {

    private final VenueRepository repository;
    private final VenueMapper mapper;

    @Override
    public VenueDTO create(VenueDTO dto) {
        VenueEntity saved = repository.save(mapper.toEntity(dto));
        return mapper.toDTO(saved);
    }

    @Override
    public VenueDTO update(Long id, VenueDTO dto) {
        VenueEntity entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Venue no encontrado"));

        entity.setName(dto.getName());
        entity.setCapacity(dto.getCapacity());
        entity.setAddress(dto.getAddress());

        return mapper.toDTO(repository.save(entity));
    }

    @Override
    public VenueDTO findById(Long id) {
        VenueEntity entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Venue no encontrado"));
        return mapper.toDTO(entity);
    }

    @Override
    public List<VenueDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Venue no encontrado");
        }
        repository.deleteById(id);
    }
}

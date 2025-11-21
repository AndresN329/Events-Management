package com.riwi.events_management.service;

import com.riwi.events_management.exception.ResourceNotFoundException;
import com.riwi.events_management.repository.EventRepository;
import com.riwi.events_management.dto.EventDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository repository;

    public List<EventDTO> getAll() {
        return repository.findAll();
    }

    public EventDTO getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("El evento con ID " + id + " no existe"));
    }

    public EventDTO create(EventDTO event) {
        return repository.save(event);
    }

    public EventDTO update(Long id, EventDTO updatedEvent) {

        // Verifica si existe
        EventDTO existing = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No se pudo actualizar. El evento con ID " + id + " no existe"));

        // Actualiza campos (solo si usas DTO como entidad)
        existing.setName(updatedEvent.getName());
        existing.setDate(updatedEvent.getDate());
        existing.setVenueId(updatedEvent.getVenueId());
        existing.setDescription(updatedEvent.getDescription());
        existing.setCapacity(updatedEvent.getCapacity());

        // Guarda y devuelve actualizado
        return repository.save(existing);
    }

    public void delete(Long id) {
        boolean deleted = repository.delete(id);

        if (!deleted) {
            throw new ResourceNotFoundException("No se pudo eliminar. El evento con ID " + id + " no existe");
        }
    }
}

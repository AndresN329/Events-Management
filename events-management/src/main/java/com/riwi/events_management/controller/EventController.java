package com.riwi.events_management.controller;

import com.riwi.events_management.dto.EventDTO;
import com.riwi.events_management.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @GetMapping
    public List<EventDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EventDTO> create(@RequestBody EventDTO event) {
        if (event.getName() == null || event.getName().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(service.create(event));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}

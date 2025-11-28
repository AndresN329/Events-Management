package com.riwi.events_management.infrastructure.adapters.in.web;

import com.riwi.events_management.domain.model.Event;
import com.riwi.events_management.domain.ports.in.event.CreateEventUseCase;
import com.riwi.events_management.domain.ports.in.event.DeleteEventUseCase;
import com.riwi.events_management.domain.ports.in.event.GetEventUseCaseById;
import com.riwi.events_management.domain.ports.in.event.GetFilteredEventsUseCase;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/events")
@Tag(name = "Events")
public class EventRestAdapter {

    private final CreateEventUseCase createUseCase;
    private final GetEventUseCaseById getUseCase;
    private final GetFilteredEventsUseCase getFilteredUseCase;
    private final DeleteEventUseCase deleteUseCase;

    public EventRestAdapter(
            CreateEventUseCase createUseCase,
            GetEventUseCaseById getUseCase,
            GetFilteredEventsUseCase getFilteredUseCase,
            DeleteEventUseCase deleteUseCase
    ) {
        this.createUseCase = createUseCase;
        this.getUseCase = getUseCase;
        this.getFilteredUseCase = getFilteredUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<Event> create(@RequestBody Event event) {
        return ResponseEntity.ok(createUseCase.create(event));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> findById(@PathVariable Long id) {
        return getUseCase.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * HU4 → Filtros + Paginación
     */
    @GetMapping
    public ResponseEntity<Page<Event>> findFiltered(
            @RequestParam(required = false) Long venueId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Pageable pageable
    ) {
        Page<Event> events = getFilteredUseCase.findFiltered(
                venueId,
                category,
                startDate,
                endDate,
                pageable
        );

        return ResponseEntity.ok(events);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteUseCase.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

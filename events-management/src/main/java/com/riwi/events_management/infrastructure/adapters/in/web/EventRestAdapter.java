package com.riwi.events_management.infrastructure.adapters.in.web;

import com.riwi.events_management.application.dto.request.EventRequest;
import com.riwi.events_management.application.dto.response.EventResponse;
import com.riwi.events_management.application.dto.validation.Create;
import com.riwi.events_management.domain.model.Event;
import com.riwi.events_management.domain.ports.in.event.CreateEventUseCase;
import com.riwi.events_management.domain.ports.in.event.DeleteEventUseCase;
import com.riwi.events_management.domain.ports.in.event.GetEventUseCaseById;
import com.riwi.events_management.domain.ports.in.event.GetFilteredEventsUseCase;
import com.riwi.events_management.infrastructure.mapper.EventMapper;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/events")
@Tag(name = "Events")
public class EventRestAdapter {

    private static final Logger log = LoggerFactory.getLogger(EventRestAdapter.class);

    private final CreateEventUseCase createUseCase;
    private final GetEventUseCaseById getUseCase;
    private final GetFilteredEventsUseCase getFilteredUseCase;
    private final DeleteEventUseCase deleteUseCase;
    private final EventMapper mapper;

    public EventRestAdapter(
            CreateEventUseCase createUseCase,
            GetEventUseCaseById getUseCase,
            GetFilteredEventsUseCase getFilteredUseCase,
            DeleteEventUseCase deleteUseCase,
            EventMapper mapper
    ) {
        this.createUseCase = createUseCase;
        this.getUseCase = getUseCase;
        this.getFilteredUseCase = getFilteredUseCase;
        this.deleteUseCase = deleteUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EventResponse> create(
            @Validated(Create.class) @RequestBody EventRequest request
    ) {
        log.info("Creando evento con nombre: {}", request.getName());
        Event event = mapper.toDomain(request);
        Event created = createUseCase.create(event);
        log.info("Evento creado con ID: {}", created.getId());
        return ResponseEntity.ok(mapper.toResponse(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> findById(@PathVariable Long id) {
        log.info("Buscando evento con ID {}", id);
        return getUseCase.findById(id)
                .map(mapper::toResponse)
                .map(res -> {
                    log.info("Evento encontrado con ID {}", id);
                    return ResponseEntity.ok(res);
                })
                .orElseGet(() -> {
                    log.warn("Evento no encontrado con ID {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @GetMapping
    public ResponseEntity<Page<EventResponse>> findFiltered(
            @RequestParam(required = false) Long venueId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @ParameterObject Pageable pageable
    ) {
        log.info("Filtrando eventos. venueId={}, category={}, startDate={}, endDate={}",
                venueId, category, startDate, endDate);

        Page<Event> events = getFilteredUseCase.findFiltered(
                venueId, category, startDate, endDate, pageable
        );

        log.info("Eventos filtrados: {}", events.getTotalElements());
        return ResponseEntity.ok(events.map(mapper::toResponse));
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.warn("Eliminando evento con ID {}", id);
        deleteUseCase.deleteById(id);
        log.info("Evento eliminado con ID {}", id);
        return ResponseEntity.noContent().build();
    }
}

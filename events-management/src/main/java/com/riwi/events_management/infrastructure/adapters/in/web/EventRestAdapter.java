package com.riwi.events_management.infrastructure.adapters.in.web;

import com.riwi.events_management.domain.model.Event;
import com.riwi.events_management.domain.ports.in.event.CreateEventUseCase;
import com.riwi.events_management.domain.ports.in.event.DeleteEventUseCase;
import com.riwi.events_management.domain.ports.in.event.GetAllEventsUseCase;
import com.riwi.events_management.domain.ports.in.event.GetEventUseCaseById;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@Tag(name = "Events")
public class EventRestAdapter {

    private final CreateEventUseCase createUseCase;
    private final GetEventUseCaseById getUseCase;
    private final GetAllEventsUseCase getAllUseCase;
    private final DeleteEventUseCase deleteUseCase;

    public EventRestAdapter(
            CreateEventUseCase createUseCase,
            GetEventUseCaseById getUseCase,
            GetAllEventsUseCase getAllUseCase,
            DeleteEventUseCase deleteUseCase
    ) {
        this.createUseCase = createUseCase;
        this.getUseCase = getUseCase;
        this.getAllUseCase = getAllUseCase;
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

    @GetMapping
    public ResponseEntity<List<Event>> getAll() {
        return ResponseEntity.ok(getAllUseCase.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteUseCase.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

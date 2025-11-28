package com.riwi.events_management.infrastructure.adapters.in.web;

import com.riwi.events_management.domain.model.Venue;
import com.riwi.events_management.domain.ports.in.venue.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/venues")
@Tag(name = "Venues")
public class VenueRestAdapter {

    private final CreateVenueUseCase createUseCase;
    private final GetVenueUseCaseById getUseCase;
    private final DeleteVenueUseCase deleteUseCase;
    private final GetFilteredVenuesUseCase filterUseCase;

    public VenueRestAdapter(
            CreateVenueUseCase createUseCase,
            GetVenueUseCaseById getUseCase,
            DeleteVenueUseCase deleteUseCase,
            GetFilteredVenuesUseCase filterUseCase
    ) {
        this.createUseCase = createUseCase;
        this.getUseCase = getUseCase;
        this.deleteUseCase = deleteUseCase;
        this.filterUseCase = filterUseCase;
    }

    @PostMapping
    public ResponseEntity<Venue> create(@RequestBody Venue venue) {
        return ResponseEntity.ok(createUseCase.create(venue));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venue> findById(@PathVariable Long id) {
        return getUseCase.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔥 Nuevo — filtros + paginación (HU4)
    @GetMapping
    public ResponseEntity<?> getFilteredVenues(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) Integer minCapacity,
            @RequestParam(required = false) Integer maxCapacity,
            Pageable pageable
    ) {
        return ResponseEntity.ok(
                filterUseCase.findAllWithFilters(
                        name, address, minCapacity, maxCapacity, pageable
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteUseCase.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

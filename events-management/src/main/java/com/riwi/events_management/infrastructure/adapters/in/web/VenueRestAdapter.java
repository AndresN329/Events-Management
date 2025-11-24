package com.riwi.events_management.infrastructure.adapters.in.web;

import com.riwi.events_management.domain.model.Venue;
import com.riwi.events_management.domain.ports.in.venue.CreateVenueUseCase;
import com.riwi.events_management.domain.ports.in.venue.GetAllVenuesUseCase;
import com.riwi.events_management.domain.ports.in.venue.DeleteVenueUseCase;
import com.riwi.events_management.domain.ports.in.venue.GetVenueUseCaseById;
import com.riwi.events_management.infrastructure.mapper.VenueMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venues")
@Tag(name = "Venues")
public class VenueRestAdapter {

    private final CreateVenueUseCase createUseCase;
    private final GetVenueUseCaseById getUseCase;
    private final GetAllVenuesUseCase getAllUseCase;
    private final DeleteVenueUseCase deleteUseCase;
    private final VenueMapper mapper;

    public VenueRestAdapter(
            CreateVenueUseCase createUseCase,
            GetVenueUseCaseById getUseCase,
            GetAllVenuesUseCase getAllUseCase,
            DeleteVenueUseCase deleteUseCase,
            VenueMapper mapper
    ) {
        this.createUseCase = createUseCase;
        this.getUseCase = getUseCase;
        this.getAllUseCase = getAllUseCase;
        this.deleteUseCase = deleteUseCase;
        this.mapper = mapper;
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

    @GetMapping
    public ResponseEntity<List<Venue>> getAll() {
        return ResponseEntity.ok(getAllUseCase.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteUseCase.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

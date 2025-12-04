package com.riwi.events_management.infrastructure.adapters.in.web;

import com.riwi.events_management.application.dto.request.VenueRequest;
import com.riwi.events_management.application.dto.response.VenueResponse;
import com.riwi.events_management.application.dto.validation.Create;
import com.riwi.events_management.domain.model.Venue;
import com.riwi.events_management.domain.ports.in.venue.CreateVenueUseCase;
import com.riwi.events_management.domain.ports.in.venue.DeleteVenueUseCase;
import com.riwi.events_management.domain.ports.in.venue.GetFilteredVenuesUseCase;
import com.riwi.events_management.domain.ports.in.venue.GetVenueUseCaseById;
import com.riwi.events_management.infrastructure.mapper.VenueMapper;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/venues")
@Tag(name = "Venues")
public class VenueRestAdapter {

    private static final Logger log = LoggerFactory.getLogger(VenueRestAdapter.class);

    private final CreateVenueUseCase createUseCase;
    private final GetVenueUseCaseById getUseCase;
    private final DeleteVenueUseCase deleteUseCase;
    private final GetFilteredVenuesUseCase filterUseCase;
    private final VenueMapper mapper;

    public VenueRestAdapter(
            CreateVenueUseCase createUseCase,
            GetVenueUseCaseById getUseCase,
            DeleteVenueUseCase deleteUseCase,
            GetFilteredVenuesUseCase filterUseCase,
            VenueMapper mapper
    ) {
        this.createUseCase = createUseCase;
        this.getUseCase = getUseCase;
        this.deleteUseCase = deleteUseCase;
        this.filterUseCase = filterUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<VenueResponse> create(
            @Validated(Create.class) @RequestBody VenueRequest request
    ) {
        log.info("Creando venue con nombre: {}", request.getName());
        Venue venue = mapper.toDomain(request);
        Venue created = createUseCase.create(venue);
        log.info("Venue creado con ID: {}", created.getId());
        return ResponseEntity.ok(mapper.toResponse(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VenueResponse> findById(@PathVariable Long id) {
        log.info("Buscando venue con ID {}", id);

        return getUseCase.findById(id)
                .map(mapper::toResponse)
                .map(response -> {
                    log.info("Venue encontrado con ID {}", id);
                    return ResponseEntity.ok(response);
                })
                .orElseGet(() -> {
                    log.warn("Venue no encontrado con ID {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @GetMapping
    public ResponseEntity<Page<VenueResponse>> getFilteredVenues(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) Integer minCapacity,
            @RequestParam(required = false) Integer maxCapacity,
            @ParameterObject Pageable pageable

    ) {
        log.info("Filtrando venues. name={}, address={}, minCap={}, maxCap={}",
                name, address, minCapacity, maxCapacity);

        Page<Venue> venues = filterUseCase.findAllWithFilters(
                name, address, minCapacity, maxCapacity, pageable
        );

        log.info("Venues encontrados: {}", venues.getTotalElements());

        return ResponseEntity.ok(venues.map(mapper::toResponse));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.warn("Eliminando venue con ID {}", id);
        deleteUseCase.deleteById(id);
        log.info("Venue eliminado con ID {}", id);
        return ResponseEntity.noContent().build();
    }
}

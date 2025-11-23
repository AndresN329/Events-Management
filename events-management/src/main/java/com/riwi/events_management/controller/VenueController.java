package com.riwi.events_management.controller;

import com.riwi.events_management.dto.VenueDTO;
import com.riwi.events_management.service.VenueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/venues")
public class VenueController {

    private final VenueService service;

    // CREATE
    @PostMapping
    public ResponseEntity<VenueDTO> create(@Valid @RequestBody VenueDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<VenueDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody VenueDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<VenueDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<VenueDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

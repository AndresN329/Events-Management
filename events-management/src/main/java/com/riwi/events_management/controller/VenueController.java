package com.riwi.events_management.controller;


import com.riwi.ticketup.dto.VenueDTO;
import com.riwi.events_management.service.VenueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venues")
@RequiredArgsConstructor
@Tag(name = "Venues", description = "Gestión de venues (lugares) del catálogo")
public class VenueController {
    private final VenueService service;

    // GET ALL
    @GetMapping
    @Operation(
            summary = "Listar todos los venues",
            description = "Obtiene todos los venues almacenados en memoria."
    )
    public List<VenueDTO> getAll() {
        return service.getAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener venue por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Venue encontrado"),
            @ApiResponse(responseCode = "404", description = "No existe un venue con ese ID")
    })
    public ResponseEntity<VenueDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // CREATE
    @PostMapping
    @Operation(
            summary = "Crear un nuevo venue",
            description = "Registra un venue en memoria con los datos proporcionados."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Venue creado exitosamente",
                    content = @Content(
                            schema = @Schema(implementation = VenueDTO.class),
                            examples = @ExampleObject(value = """
                                    {
                                      "name": "Estadio Central",
                                      "capacity": 50000,
                                      "address": "Av. Libertador 123"
                                    }
                                    """)
                    )),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<VenueDTO> create(@RequestBody VenueDTO venue) {

        if (venue.getName() == null || venue.getName().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(service.create(venue));
    }

    // DELETE
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar venue por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Venue eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "No existe un venue con ese ID")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
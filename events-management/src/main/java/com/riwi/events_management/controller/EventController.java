package com.riwi.events_management.controller;

import com.riwi.events_management.dto.EventDTO;
import com.riwi.events_management.service.EventService;
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
@RequestMapping("/events")
@RequiredArgsConstructor
@Tag(name = "Events", description = "Gestión de eventos del catálogo")
public class EventController {

    private final EventService service;

    @GetMapping
    @Operation(summary = "Listar todos los eventos", description = "Retorna una lista completa de eventos almacenados en memoria.")
    public List<EventDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener evento por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Evento encontrado"),
            @ApiResponse(responseCode = "404", description = "No existe un evento con ese ID")
    })
    public ResponseEntity<EventDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    @Operation(
            summary = "Crear un nuevo evento",
            description = "Crea un evento con los atributos enviados en el cuerpo de la petición."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Evento creado exitosamente",
                    content = @Content(schema = @Schema(implementation = EventDTO.class),
                            examples = @ExampleObject(
                                    value = """
                                    {
                                      "name": "Concierto de Rock",
                                      "description": "Show especial con bandas invitadas",
                                      "venueId": 1
                                    }
                                    """
                            ))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<EventDTO> create(@RequestBody EventDTO event) {

        if (event.getName() == null || event.getName().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(service.create(event));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> update(@PathVariable Long id, @RequestBody EventDTO event) {

        if (event.getName() == null || event.getName().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        EventDTO updated = service.update(id, event);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar evento por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Evento eliminado"),
            @ApiResponse(responseCode = "404", description = "No existe un evento con ese ID")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
package com.riwi.events_management.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(
        name = "Event",
        description = "Representa un evento dentro del catálogo, incluyendo su nombre, fecha, capacidad y el ID del venue donde se realizará."
)
public class EventDTO {

    @Schema(
            description = "Identificador único del evento",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Nombre del evento. No puede estar vacío.",
            example = "Concierto de Rock"
    )
    private String name;

    @Schema(
            description = "Descripción detallada del evento",
            example = "Un espectáculo musical con artistas invitados."
    )
    private String description;

    @Schema(
            description = "Fecha del evento en formato YYYY-MM-DD",
            example = "2025-06-15"
    )
    private LocalDate date;

    @Schema(
            description = "Capacidad máxima del evento",
            example = "5000"
    )
    private Integer capacity;

    @Schema(
            description = "ID del venue donde se realizará el evento",
            example = "5"
    )
    private Long venueId;
}

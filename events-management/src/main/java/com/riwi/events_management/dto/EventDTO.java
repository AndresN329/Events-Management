package com.riwi.events_management.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(
        name = "Event",
        description = "Representa un evento dentro del catálogo, incluyendo nombre, categoría, ciudad, fechas, capacidad y venue."
)
public class EventDTO {

    @Schema(description = "Identificador único del evento", example = "1")
    private Long id;

    @NotBlank(message = "El nombre del evento es obligatorio")
    @Schema(description = "Nombre del evento", example = "Concierto de Rock")
    private String name;

    @Schema(description = "Descripción detallada del evento", example = "Un espectáculo musical con artistas invitados.")
    private String description;

    @NotBlank(message = "La categoría es obligatoria")
    @Schema(description = "Categoría del evento", example = "Música")
    private String category;

    @NotBlank(message = "La ciudad es obligatoria")
    @Schema(description = "Ciudad donde se realiza el evento", example = "Medellín")
    private String city;

    @Future(message = "La fecha de inicio debe ser futura")
    @Schema(description = "Fecha de inicio del evento", example = "2025-06-15")
    private LocalDate startDate;

    @Positive(message = "La capacidad debe ser un número mayor a cero")
    @Schema(description = "Capacidad máxima del evento", example = "5000")
    private Integer capacity;

    @NotNull(message = "El ID del venue es obligatorio")
    @Schema(description = "ID del venue donde se realizará el evento", example = "5")
    private Long venueId;
}

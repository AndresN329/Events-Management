package com.riwi.events_management.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(
        name = "Venue",
        description = "Representa un venue o lugar donde se realizan los eventos."
)
public class VenueDTO {

    @Schema(description = "Identificador único del venue", example = "10")
    private Long id;

    @NotBlank(message = "El nombre del venue es obligatorio")
    @Schema(description = "Nombre del venue. No puede estar vacío.", example = "Estadio Central")
    private String name;

    @Positive(message = "La capacidad debe ser un número mayor a cero")
    @Schema(description = "Capacidad máxima del venue", example = "50000")
    private Integer capacity;

    @NotBlank(message = "La dirección del venue es obligatoria")
    @Schema(description = "Dirección física del venue", example = "Avenida Libertador 123, Bogotá")
    private String address;
}

package com.riwi.ticketup.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name = "Venue",
        description = "Representa un venue o lugar donde se realizan los eventos."
)
public class VenueDTO {

    @Schema(
            description = "Identificador único del venue",
            example = "10"
    )
    private Long id;

    @Schema(
            description = "Nombre del venue. No puede estar vacío.",
            example = "Estadio Central"
    )
    private String name;

    @Schema(
            description = "Capacidad máxima del venue",
            example = "50000"
    )
    private Integer capacity;

    @Schema(
            description = "Dirección física del venue",
            example = "Avenida Libertador 123, Bogotá"
    )
    private String address;
}
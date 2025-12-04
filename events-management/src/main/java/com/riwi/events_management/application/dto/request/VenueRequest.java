package com.riwi.events_management.application.dto.request;

import com.riwi.events_management.application.dto.validation.Create;
import com.riwi.events_management.application.dto.validation.Update;
import jakarta.validation.constraints.*;

public class VenueRequest {

    @NotBlank(message = "El nombre del venue es obligatorio", groups = {Create.class})
    private String name;

    @NotBlank(message = "La dirección del venue es obligatoria", groups = {Create.class})
    private String address;

    @NotNull(message = "La capacidad mínima es obligatoria", groups = {Create.class})
    @Min(value = 1, message = "La capacidad mínima debe ser mayor a 0", groups = {Create.class, Update.class})
    private Integer minCapacity;

    @NotNull(message = "La capacidad máxima es obligatoria", groups = {Create.class})
    @Min(value = 1, message = "La capacidad máxima debe ser mayor a 0", groups = {Create.class, Update.class})
    private Integer maxCapacity;

    @Size(max = 500, message = "La descripción no debe superar 500 caracteres", groups = {Create.class, Update.class})
    private String description;

    // VALIDACIÓN PERSONALIZADA
    @AssertTrue(message = "La capacidad máxima debe ser mayor o igual a la mínima",
            groups = {Create.class, Update.class})
    private boolean isCapacityValid() {
        if (minCapacity == null || maxCapacity == null) return true;
        return maxCapacity >= minCapacity;
    }

    // ====== GETTERS & SETTERS ======

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Integer getMinCapacity() { return minCapacity; }
    public void setMinCapacity(Integer minCapacity) { this.minCapacity = minCapacity; }

    public Integer getMaxCapacity() { return maxCapacity; }
    public void setMaxCapacity(Integer maxCapacity) { this.maxCapacity = maxCapacity; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}

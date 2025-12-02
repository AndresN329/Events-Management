package com.riwi.events_management.application.dto.request;

import com.riwi.events_management.application.dto.validation.Create;
import com.riwi.events_management.application.dto.validation.Update;
import com.riwi.events_management.application.dto.validation.ValidDateRange;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@ValidDateRange(message = "startDate debe ser anterior a endDate")
public class EventRequest {

    @NotBlank(message = "El nombre del evento es obligatorio", groups = {Create.class})
    private String name;

    @NotBlank(message = "La categoría es obligatoria", groups = {Create.class})
    private String category;

    @NotNull(message = "La fecha de inicio es obligatoria", groups = {Create.class})
    private LocalDateTime startDate;

    @NotNull(message = "La fecha de fin es obligatoria", groups = {Create.class})
    private LocalDateTime endDate;

    @NotNull(message = "El ID del venue es obligatorio", groups = {Create.class})
    @Positive
    private Long venueId;

    @NotNull(message = "La capacidad es obligatoria", groups = {Create.class})
    @Min(value = 1)
    private Integer capacity;

    @Size(max = 500)
    private String description;

    // Getters y setters...

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public LocalDateTime getStartDate() { return startDate; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }

    public LocalDateTime getEndDate() { return endDate; }
    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }

    public Long getVenueId() { return venueId; }
    public void setVenueId(Long venueId) { this.venueId = venueId; }

    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}

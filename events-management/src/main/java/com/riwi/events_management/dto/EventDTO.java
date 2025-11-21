package com.riwi.events_management.dto;

import java.time.LocalDate;

public class EventDTO {
    private Long id;
    private String name;
    private LocalDate date;
    private String location;
    private Long venueId;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Long getVenueId() { return venueId; }
    public void setVenueId(Long venueId) { this.venueId = venueId; }
}

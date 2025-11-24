package com.riwi.events_management.domain.model;

import java.time.LocalDate;

public class Event {

    private Long id;
    private String name;
    private String description;
    private String category;
    private String city;
    private LocalDate startDate;
    private Integer capacity;
    private Long venueId;

    public Event() {}

    public Event(Long id, String name, String description, String category,
                 String city, LocalDate startDate, Integer capacity, Long venueId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.city = city;
        this.startDate = startDate;
        this.capacity = capacity;
        this.venueId = venueId;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Long getVenueId() {
        return venueId;
    }

    public void setVenueId(Long venueId) {
        this.venueId = venueId;
    }
}

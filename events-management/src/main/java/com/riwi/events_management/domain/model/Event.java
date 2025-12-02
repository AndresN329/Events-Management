package com.riwi.events_management.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event {

    private Long id;
    private String name;
    private String description;
    private String category;
    private String city;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer capacity;
    private Long venueId;

    public Event() {
    }

    public Event(Long id, String name, String description, String category,
                 String city, LocalDateTime startDate, LocalDateTime endDate,
                 Integer capacity, Long venueId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.city = city;
        this.startDate = startDate;
        this.endDate = endDate;
        this.capacity = capacity;
        this.venueId = venueId;
    }

    // Getters & setters ↓↓↓


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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
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
package com.riwi.events_management.dto;

public class VenueDTO {
    private Long id;
    private String name;
    private int capacity;
    private String address;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}

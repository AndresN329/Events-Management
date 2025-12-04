package com.riwi.events_management.domain.model;

public class Venue {

    private Long id;
    private String name;
    private String address;

    private Integer minCapacity;
    private Integer maxCapacity;
    private String description;

    public Venue() {}

    public Venue(Long id, String name, String address, Integer minCapacity, Integer maxCapacity, String description) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.minCapacity = minCapacity;
        this.maxCapacity = maxCapacity;
        this.description = description;
    }

    // getters & setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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

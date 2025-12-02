package com.riwi.events_management.infrastructure.adapters.out.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "venues")
public class VenueJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Integer minCapacity;

    @Column(nullable = false)
    private Integer maxCapacity;

    @Column(length = 500)
    private String description;

    @Builder.Default
    @OneToMany(
            mappedBy = "venue",
            cascade = CascadeType.ALL,
            orphanRemoval = false,
            fetch = FetchType.LAZY
    )
    private List<EventJpaEntity> events = new ArrayList<>();

    // Helpers
    public void addEvent(EventJpaEntity event) {
        events.add(event);
        event.setVenue(this);
    }

    public void removeEvent(EventJpaEntity event) {
        events.remove(event);
        event.setVenue(null);
    }
}

package com.riwi.events_management.infrastructure.adapters.out.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
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
    private Integer capacity;

    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<EventJpaEntity> events;
}

package com.riwi.events_management.infrastructure.adapters.out.jpa.repository;

import com.riwi.events_management.infrastructure.adapters.out.jpa.entity.VenueJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataVenueRepository extends JpaRepository<VenueJpaEntity, Long> {
}

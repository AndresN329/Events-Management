package com.riwi.events_management.infrastructure.adapters.out.jpa.repository;

import com.riwi.events_management.infrastructure.adapters.out.jpa.entity.EventJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SpringDataEventRepository
        extends JpaRepository<EventJpaEntity, Long>,
        JpaSpecificationExecutor<EventJpaEntity> {

    // Opcionales (útiles para HU4)
    // List<EventJpaEntity> findByVenue_Id(Long venueId);
    // List<EventJpaEntity> findByStartDateBetween(LocalDate start, LocalDate end);
}

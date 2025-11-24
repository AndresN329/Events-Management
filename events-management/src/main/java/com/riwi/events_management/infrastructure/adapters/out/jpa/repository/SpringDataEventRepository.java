package com.riwi.events_management.infrastructure.adapters.out.jpa.repository;

import com.riwi.events_management.infrastructure.adapters.out.jpa.entity.EventJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataEventRepository extends JpaRepository<EventJpaEntity, Long> {
}

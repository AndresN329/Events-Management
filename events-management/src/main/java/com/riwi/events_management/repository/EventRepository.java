package com.riwi.events_management.repository;

import com.riwi.events_management.entity.EventEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EventRepository extends JpaRepository<EventEntity, Long> {

    boolean existsByName(String name);

    @Query("""
        SELECT e FROM EventEntity e 
        WHERE (:city IS NULL OR e.city = :city)
        AND   (:category IS NULL OR e.category = :category)
        AND   (:startDate IS NULL OR e.startDate >= :startDate)
    """)
    Page<EventEntity> filter(
            @Param("city") String city,
            @Param("category") String category,
            @Param("startDate") java.time.LocalDate startDate,
            Pageable pageable
    );
}

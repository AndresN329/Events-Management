package com.riwi.events_management.infrastructure.adapters.out.jpa.specification;

import com.riwi.events_management.infrastructure.adapters.out.jpa.entity.EventJpaEntity;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class EventSpecifications {

    public static Specification<EventJpaEntity> byVenue(Long venueId) {
        return (root, query, cb) ->
                venueId == null
                        ? cb.conjunction()
                        : cb.equal(root.get("venue").get("id"), venueId);
    }

    public static Specification<EventJpaEntity> byCategory(String category) {
        return (root, query, cb) ->
                category == null || category.isBlank()
                        ? cb.conjunction()
                        : cb.equal(root.get("category"), category);
    }

    public static Specification<EventJpaEntity> byDateRange(LocalDateTime start, LocalDateTime end) {
        return (root, query, cb) -> {

            if (start == null && end == null)
                return cb.conjunction();

            if (start != null && end != null)
                return cb.between(root.get("startDate"), start, end);

            if (start != null)
                return cb.greaterThanOrEqualTo(root.get("startDate"), start);

            return cb.lessThanOrEqualTo(root.get("startDate"), end);
        };
    }

    // Para evitar N+1 si se requiere
    public static Specification<EventJpaEntity> fetchVenue() {
        return (root, query, cb) -> {
            root.fetch("venue", JoinType.LEFT);
            return cb.conjunction();
        };
    }
}

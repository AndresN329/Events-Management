package com.riwi.events_management.infrastructure.adapters.out.jpa.specification;

import com.riwi.events_management.infrastructure.adapters.out.jpa.entity.VenueJpaEntity;
import org.springframework.data.jpa.domain.Specification;

public class VenueSpecifications {

    public static Specification<VenueJpaEntity> byName(String name) {
        return (root, query, cb) ->
                name == null || name.isBlank()
                        ? cb.conjunction()
                        : cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<VenueJpaEntity> byAddress(String address) {
        return (root, query, cb) ->
                address == null || address.isBlank()
                        ? cb.conjunction()
                        : cb.like(cb.lower(root.get("address")), "%" + address.toLowerCase() + "%");
    }

    // ⛔ ELIMINADO: ya no existe el campo "capacity"
    // public static Specification<VenueJpaEntity> byCapacity(Integer capacity) { ... }

    public static Specification<VenueJpaEntity> minCapacity(Integer min) {
        return (root, query, cb) ->
                min == null
                        ? cb.conjunction()
                        : cb.greaterThanOrEqualTo(root.get("maxCapacity"), min);
    }

    public static Specification<VenueJpaEntity> maxCapacity(Integer max) {
        return (root, query, cb) ->
                max == null
                        ? cb.conjunction()
                        : cb.lessThanOrEqualTo(root.get("minCapacity"), max);
    }
}

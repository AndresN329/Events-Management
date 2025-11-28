package com.riwi.events_management.infrastructure.adapters.out.jpa.specification;

import com.riwi.events_management.infrastructure.adapters.out.jpa.entity.VenueJpaEntity;
import org.springframework.data.jpa.domain.Specification;

public class VenueSpecifications {

    public static Specification<VenueJpaEntity> byName(String name) {
        return (root, query, cb) ->
                name == null || name.isBlank()
                        ? cb.conjunction()
                        : cb.equal(root.get("name"), name);
    }

    public static Specification<VenueJpaEntity> byAddress(String address) {
        return (root, query, cb) ->
                address == null || address.isBlank()
                        ? cb.conjunction()
                        : cb.equal(root.get("address"), address);
    }

    public static Specification<VenueJpaEntity> byCapacity(Integer capacity) {
        return (root, query, cb) ->
                capacity == null
                        ? cb.conjunction()
                        : cb.equal(root.get("capacity"), capacity);
    }

    public static Specification<VenueJpaEntity> minCapacity(Integer min) {
        return (root, query, cb) ->
                min == null
                        ? cb.conjunction()
                        : cb.greaterThanOrEqualTo(root.get("capacity"), min);
    }

    public static Specification<VenueJpaEntity> maxCapacity(Integer max) {
        return (root, query, cb) ->
                max == null
                        ? cb.conjunction()
                        : cb.lessThanOrEqualTo(root.get("capacity"), max);
    }
}

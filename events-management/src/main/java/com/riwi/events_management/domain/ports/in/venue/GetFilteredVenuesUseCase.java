package com.riwi.events_management.domain.ports.in.venue;

import com.riwi.events_management.domain.model.Venue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetFilteredVenuesUseCase {

    Page<Venue> findAllWithFilters(
            String name,
            String address,
            Integer minCapacity,
            Integer maxCapacity,
            Pageable pageable
    );
}

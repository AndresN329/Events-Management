package com.riwi.events_management.application.usecase.venue;

import com.riwi.events_management.domain.model.Venue;
import com.riwi.events_management.domain.ports.out.venue.VenueRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetFilteredVenuesUseCaseImplTest {

    @Mock
    private VenueRepositoryPort repository;

    @InjectMocks
    private GetFilteredVenuesUseCaseImpl useCase;

    private Venue v1;
    private Venue v2;

    private Pageable pageable;

    @BeforeEach
    void setUp() {
        v1 = new Venue();
        v1.setId(1L);
        v1.setName("Centro A");
        v1.setAddress("Medellín");
        v1.setMinCapacity(50);
        v1.setMaxCapacity(500);

        v2 = new Venue();
        v2.setId(2L);
        v2.setName("Centro B");
        v2.setAddress("Bogotá");
        v2.setMinCapacity(100);
        v2.setMaxCapacity(1000);

        pageable = PageRequest.of(0, 10);
    }

    @Test
    void findAllWithFilters_ShouldReturnPageOfVenues() {
        String name = "Centro";
        String address = "Medellín";
        Integer minCapacity = 50;
        Integer maxCapacity = 1000;

        Page<Venue> mockPage = new PageImpl<>(List.of(v1, v2));

        when(repository.findAllWithFilters(
                name,
                address,
                minCapacity,
                maxCapacity,
                pageable
        )).thenReturn(mockPage);

        // Act
        Page<Venue> result = useCase.findAllWithFilters(
                name,
                address,
                minCapacity,
                maxCapacity,
                pageable
        );

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        assertEquals("Centro A", result.getContent().get(0).getName());
        assertEquals("Centro B", result.getContent().get(1).getName());

        verify(repository, times(1)).findAllWithFilters(
                name,
                address,
                minCapacity,
                maxCapacity,
                pageable
        );
    }
}

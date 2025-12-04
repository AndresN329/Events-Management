package com.riwi.events_management.application.usecase.venue;

import com.riwi.events_management.domain.model.Venue;
import com.riwi.events_management.domain.ports.out.venue.VenueRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateVenueUseCaseImplTest {

    @Mock
    private VenueRepositoryPort repository;

    @InjectMocks
    private CreateVenueUseCaseImpl useCase;

    private Venue venueToSave;
    private Venue venueSaved;

    @BeforeEach
    void setUp() {
        venueToSave = new Venue();
        venueToSave.setName("Estadio Central");
        venueToSave.setAddress("Bogotá");
        venueToSave.setMinCapacity(20000);
        venueToSave.setMaxCapacity(50000);

        venueSaved = new Venue();
        venueSaved.setId(10L);
        venueSaved.setName("Estadio Central");
        venueSaved.setAddress("Bogotá");
        venueSaved.setMinCapacity(20000);
        venueSaved.setMaxCapacity(50000);
    }


    @Test
    void create_ShouldSaveVenueAndReturnSavedEntity() {
        // Arrange
        when(repository.save(venueToSave)).thenReturn(venueSaved);

        // Act
        Venue result = useCase.create(venueToSave);

        // Assert
        assertEquals(10L, result.getId());
        assertEquals("Estadio Central", result.getName());
        assertEquals("Bogotá", result.getAddress());
        assertEquals(20000, result.getMinCapacity());
        assertEquals(50000, result.getMaxCapacity());

    }
}

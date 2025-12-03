package com.riwi.events_management.application.usecase.venue;

import com.riwi.events_management.domain.model.Venue;
import com.riwi.events_management.domain.ports.out.venue.VenueRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetVenueUseCaseImplTest {

    @Mock
    private VenueRepositoryPort repository;

    @InjectMocks
    private GetVenueUseCaseImpl useCase;

    private Venue venue;

    @BeforeEach
    void setUp() {
        venue = new Venue();
        venue.setId(5L);
        venue.setName("Centro de Convenciones");
        venue.setAddress("Medellín");
        venue.setMinCapacity(100);
        venue.setMaxCapacity(5000);
    }

    @Test
    void findById_ShouldReturnVenue_WhenExists() {
        when(repository.findById(5L)).thenReturn(Optional.of(venue));

        Optional<Venue> result = useCase.findById(5L);

        assertTrue(result.isPresent());
        assertEquals(5L, result.get().getId());
        assertEquals("Centro de Convenciones", result.get().getName());

        verify(repository, times(1)).findById(5L);
    }

    @Test
    void findById_ShouldReturnEmpty_WhenNotFound() {
        when(repository.findById(5L)).thenReturn(Optional.empty());

        Optional<Venue> result = useCase.findById(5L);

        assertFalse(result.isPresent());
        verify(repository, times(1)).findById(5L);
    }
}

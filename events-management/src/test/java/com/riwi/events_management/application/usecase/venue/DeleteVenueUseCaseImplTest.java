package com.riwi.events_management.application.usecase.venue;

import com.riwi.events_management.domain.ports.out.venue.VenueRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteVenueUseCaseImplTest {

    @Mock
    private VenueRepositoryPort repository;

    @InjectMocks
    private DeleteVenueUseCaseImpl useCase;

    @Test
    void deleteById_ShouldCallRepositoryDeleteOnce() {
        Long id = 10L;

        // Act
        useCase.deleteById(id);

        // Assert
        verify(repository, times(1)).deleteById(id);
    }
}

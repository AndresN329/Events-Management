package com.riwi.events_management.application.usecase.event;

import com.riwi.events_management.domain.ports.out.event.EventRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteEventUseCaseImplTest {

    @Mock
    private EventRepositoryPort repository;

    @InjectMocks
    private DeleteEventUseCaseImpl useCase;

    @Test
    void deleteById_ShouldDelete_WhenEventExists() {
        Long id = 10L;

        when(repository.existsById(id)).thenReturn(true);

        useCase.deleteById(id);

        verify(repository, times(1)).existsById(id);
        verify(repository, times(1)).deleteById(id);
    }

    @Test
    void deleteById_ShouldThrowException_WhenEventDoesNotExist() {
        Long id = 99L;

        when(repository.existsById(id)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> useCase.deleteById(id));

        verify(repository, times(1)).existsById(id);
        verify(repository, never()).deleteById(anyLong());
    }
}

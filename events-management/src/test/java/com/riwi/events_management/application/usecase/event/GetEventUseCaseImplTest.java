package com.riwi.events_management.application.usecase.event;

import com.riwi.events_management.domain.model.Event;
import com.riwi.events_management.domain.ports.out.event.EventRepositoryPort;
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
class GetEventUseCaseImplTest {

    @Mock
    private EventRepositoryPort repository;

    @InjectMocks
    private GetEventUseCaseImpl useCase;

    private Event event;

    @BeforeEach
    void setUp() {
        event = new Event();
        event.setId(5L);
        event.setName("Festival de Rock");
    }

    @Test
    void findById_ShouldReturnEvent_WhenExists() {
        when(repository.findById(5L)).thenReturn(Optional.of(event));

        Optional<Event> result = useCase.findById(5L);

        assertTrue(result.isPresent());
        assertEquals(5L, result.get().getId());
        assertEquals("Festival de Rock", result.get().getName());

        verify(repository, times(1)).findById(5L);
    }

    @Test
    void findById_ShouldReturnEmpty_WhenNotFound() {
        when(repository.findById(5L)).thenReturn(Optional.empty());

        Optional<Event> result = useCase.findById(5L);

        assertFalse(result.isPresent());
        verify(repository, times(1)).findById(5L);
    }
}

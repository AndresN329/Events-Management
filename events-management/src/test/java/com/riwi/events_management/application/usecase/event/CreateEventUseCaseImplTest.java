package com.riwi.events_management.application.usecase.event;

import com.riwi.events_management.domain.model.Event;
import com.riwi.events_management.domain.ports.out.event.EventRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateEventUseCaseImplTest {

    @Mock
    private EventRepositoryPort repository;

    @InjectMocks
    private CreateEventUseCaseImpl useCase;

    private Event eventToSave;
    private Event eventSaved;

    @BeforeEach
    void setUp() {
        eventToSave = new Event();
        eventToSave.setName("Concierto");
        eventToSave.setCategory("MUSIC");
        eventToSave.setVenueId(1L);

        eventSaved = new Event();
        eventSaved.setId(100L);
        eventSaved.setName("Concierto");
        eventSaved.setCategory("MUSIC");
        eventSaved.setVenueId(1L);
    }

    @Test
    void create_ShouldSaveEventAndReturnSavedEntity() {
        // Arrange (comportamiento simulado del repositorio)
        when(repository.save(eventToSave)).thenReturn(eventSaved);

        // Act
        Event result = useCase.create(eventToSave);

        // Assert
        verify(repository, times(1)).save(eventToSave);
        assertNotNull(result);
        assertEquals(100L, result.getId());
        assertEquals("Concierto", result.getName());
    }
}

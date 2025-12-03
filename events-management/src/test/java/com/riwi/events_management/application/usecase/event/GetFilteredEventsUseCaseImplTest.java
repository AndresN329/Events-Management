package com.riwi.events_management.application.usecase.event;

import com.riwi.events_management.domain.model.Event;
import com.riwi.events_management.domain.ports.out.event.EventRepositoryPort;
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

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetFilteredEventsUseCaseImplTest {

    @Mock
    private EventRepositoryPort repository;

    @InjectMocks
    private GetFilteredEventsUseCaseImpl useCase;

    private Event e1;
    private Event e2;

    private Pageable pageable;

    @BeforeEach
    void setUp() {
        e1 = new Event();
        e1.setId(1L);
        e1.setName("Evento A");

        e2 = new Event();
        e2.setId(2L);
        e2.setName("Evento B");

        pageable = PageRequest.of(0, 10);
    }

    @Test
    void findFiltered_ShouldReturnPageOfEvents() {
        Long venueId = 1L;
        String category = "MUSIC";
        LocalDateTime startDate = LocalDateTime.of(2030, 1, 1, 18, 0);
        LocalDateTime endDate = LocalDateTime.of(2030, 1, 1, 23, 0);

        Page<Event> mockPage = new PageImpl<>(List.of(e1, e2));

        when(repository.findAllWithFilters(
                venueId,
                category,
                startDate,
                endDate,
                pageable
        )).thenReturn(mockPage);

        // Act
        Page<Event> result = useCase.findFiltered(
                venueId,
                category,
                startDate,
                endDate,
                pageable
        );

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        assertEquals("Evento A", result.getContent().get(0).getName());
        assertEquals("Evento B", result.getContent().get(1).getName());

        verify(repository, times(1)).findAllWithFilters(
                venueId,
                category,
                startDate,
                endDate,
                pageable
        );
    }
}

package com.riwi.events_management.infrastructure.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class EventMetrics {

    private final Counter eventsCreatedCounter;

    public EventMetrics(MeterRegistry registry) {
        this.eventsCreatedCounter =
                Counter.builder("events_created_total")
                        .description("Número total de eventos creados")
                        .register(registry);
    }

    public void increment() {
        eventsCreatedCounter.increment();
    }
}

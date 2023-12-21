package com.epam.gym.monitoring;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomMetricsService {

    private final Counter customCounter;

    @Autowired
    public CustomMetricsService(MeterRegistry meterRegistry) {
        this.customCounter = Counter.builder("custom_metric_counter")
                .description("A custom counter metric")
                .register(meterRegistry);
    }

    public void incrementCustomCounter() {
        customCounter.increment();
    }
}

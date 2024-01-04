package com.epam.gym.monitoring;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoggerHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        String loggerHealthIndicator = "LoggerHealthIndicator";
        if (isLoggerHealthGood()) {
            return Health.up().withDetail(loggerHealthIndicator, "Service is running").build();
        }
        return Health.down().withDetail(loggerHealthIndicator, "Service is not available").build();
    }

    private boolean isLoggerHealthGood() {
        try {
            Logger logger = LogManager.getLogger(LoggerHealthIndicator.class);
            if (logger != null) {
                logger.info("Logger health check: Service is running");
                return true;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }
}

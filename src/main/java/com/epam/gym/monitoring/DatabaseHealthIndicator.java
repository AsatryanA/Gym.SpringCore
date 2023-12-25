package com.epam.gym.monitoring;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Slf4j
@Component
@RequiredArgsConstructor
public class DatabaseHealthIndicator implements HealthIndicator {

    private final DataSource dataSource;

    @Override
    public Health health() {
        final String databaseHealthIndicator = "DatabaseHealthIndicator";
        if (isDatabaseHealthGood()) {
            return Health.up().withDetail(databaseHealthIndicator, "Service is running").build();
        }
        return Health.down().withDetail(databaseHealthIndicator, "Service is not available").build();
    }

    private boolean isDatabaseHealthGood() {
        try (var connection = dataSource.getConnection()) {
            try (var statement = connection.createStatement()) {
                statement.execute("SELECT 1");
            }
            return true;
        } catch (SQLException ex) {
            log.error(ex.getMessage());
            return false;
        }
    }
}

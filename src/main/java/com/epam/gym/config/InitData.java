package com.epam.gym.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;

@Configuration
@RequiredArgsConstructor
public class InitData {

    private final JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void initializeDatabase() {
        final var traineeCountQuery = "SELECT COUNT(*) FROM training_types";
        try {
            var rowCount = jdbcTemplate.queryForObject(traineeCountQuery, Integer.class);
            if (rowCount != null && rowCount == 0) {
                var resource = new ClassPathResource("import.sql");
                var sqlScript = new String(Files.readAllBytes(resource.getFile().toPath()));
                jdbcTemplate.execute(sqlScript);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading SQL script", e);
        }
    }
}

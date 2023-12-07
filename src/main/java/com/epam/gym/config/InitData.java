package com.epam.gym.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.nio.file.Files;

@Configuration
@RequiredArgsConstructor
public class InitData {
    private final JdbcTemplate jdbcTemplate;


    @PostConstruct
    public void initializeDatabase() {
        try {
            int rowCount = jdbcTemplate.queryForObject
                    ("SELECT COUNT(*) FROM training_types", Integer.class);

            if (rowCount == 0) {
                Resource resource = new ClassPathResource("import.sql");
                String sqlScript = new String(Files.readAllBytes(resource.getFile().toPath()));
                jdbcTemplate.execute(sqlScript);
            } else {
                System.out.println("Tables are not empty. Skipping initialization.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading SQL script", e);
        }
    }
}

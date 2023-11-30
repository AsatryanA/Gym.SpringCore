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
public class InitDataConf {
    private final JdbcTemplate jdbcTemplate;


    @PostConstruct
    public void initializeDatabase() {
        Resource resource = new ClassPathResource("import.sql");
        try {
            String sqlScript = new String(Files.readAllBytes(resource.getFile().toPath()));
            jdbcTemplate.execute(sqlScript);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.riwi.events_management.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI baseOpenAPI() {
        return new OpenAPI().info(
                new Info()
                        .title("Events & Venues API – Hexagonal Architecture")
                        .version("1.1.0")
                        .description(
                                "API using Hexagonal Architecture for managing events and venues, including dynamic filters, pagination, entity relations, transactions and Flyway migrations."
                        )
        );
    }
}

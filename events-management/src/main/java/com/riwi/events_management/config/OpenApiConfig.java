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
                        .title("In-Memory Catalog of Events and Venues")
                        .version("1.0.0")
                        .description("REST API for managing events and venues using layered architecture and in-memory persistence")
        );
    }
}
package com.thefinaldoor.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI escapeRoomOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("The Final Door API")
                        .description("API for The Final Door - A multi-themed escape room game platform")
                        .version("1.0.0"))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Local development server")
                ));
    }
}


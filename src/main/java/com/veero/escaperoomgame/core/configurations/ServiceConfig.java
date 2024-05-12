package com.veero.escaperoomgame.core.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ServiceConfig {
    //TODO: Need to utilize webclient to make calls to the game service
    @Bean(name = "GameWebClient")
    public WebClient getWebClient() {
        return WebClient.builder().build();
    }
}

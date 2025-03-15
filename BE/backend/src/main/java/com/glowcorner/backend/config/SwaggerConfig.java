package com.glowcorner.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Glow Corner API")
                        .version("1.0")
                        .description("API documentation for the Skin Care Shopping application")
                        .contact(new Contact()
                                .name("Your Name")
                                .email("your.email@example.com")
                                .url("https://yourwebsite.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://mit-license.org")));
    }

    @Bean
    public SwaggerUiConfigProperties swaggerUiConfigProperties() {
        SwaggerUiConfigProperties properties = new SwaggerUiConfigProperties();
        properties.setPath("/swagger-ui-custom");
        return properties;
    }
}
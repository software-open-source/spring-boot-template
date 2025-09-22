package org.software.open.source.spring.template.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title("Spring boot template Service API").version("1.0.0").description("API documentation for Spring boot template Service").license(new License().name("MIT License").url("https://opensource.org/licenses/MIT")));
    }

}

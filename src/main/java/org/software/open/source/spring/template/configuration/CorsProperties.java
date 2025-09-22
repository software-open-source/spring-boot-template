package org.software.open.source.spring.template.configuration;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data // hoặc dùng getter/setter thủ công
@Configuration
@ConfigurationProperties(prefix = "app.cors")
public class CorsProperties {

    private List<String> allowedOriginPatterns = List.of();
    private List<String> allowedMethods = List.of();
    private List<String> allowedHeaders = List.of();
    private List<String> exposedHeaders = List.of();
    private Boolean allowCredentials = false;
    private Long maxAge = 3600L;
}

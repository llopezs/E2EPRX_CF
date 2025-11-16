package com.lipigas.e2eprx;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> webServerFactoryCustomizer() {
        return factory -> {
            factory.addConnectorCustomizers(connector -> {
                connector.setProperty("relaxedPathChars", "");
                connector.setProperty("relaxedQueryChars", "");
            });
        };
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/mobile")
                        .allowedOrigins("*")
                        .allowedMethods("POST", "OPTIONS")
                        .allowedHeaders("method", "Authorization", "Content-Type", "VERSION", "OS_VERSION", "MODEL")
                        .maxAge(3600);
            }
        };
    }
}


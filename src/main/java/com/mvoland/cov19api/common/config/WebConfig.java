package com.mvoland.cov19api.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Value("${api.client.url}")
    String origin;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.err.println("=================================================");
        System.err.println(origin);
        System.err.println("=================================================");

        registry.addMapping("/**")
                .allowedOrigins(origin);
    }
}

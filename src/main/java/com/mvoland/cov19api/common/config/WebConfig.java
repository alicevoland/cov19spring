package com.mvoland.cov19api.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private Environment env;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String origin = env.getProperty("CLIENT_URL", "unknown");
        System.err.println("=================================================");
        System.err.println(origin);
        System.err.println("=================================================");

        registry.addMapping("/**")
                .allowedOrigins(origin);
    }
}

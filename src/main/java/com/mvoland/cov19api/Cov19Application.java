package com.mvoland.cov19api;

import com.mvoland.cov19api.datagouvfr.common.SourceService;
import org.aspectj.weaver.reflect.ReflectionShadow;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ClassUtils;

import java.util.Set;

@SpringBootApplication
public class Cov19Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Cov19Application.class);

    public static void main(String[] args) {
        new SpringApplicationBuilder(Cov19Application.class).run(args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            LOGGER.info("** START commandLineRunner **");

            LOGGER.info("** END   commandLineRunner **");
        };
    }

}

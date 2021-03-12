package com.happyminute.covidgouvfr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestClrApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestClrApplication.class);



    public static void main(String[] args) {
        new SpringApplicationBuilder(TestClrApplication.class)
//                .web(WebApplicationType.NONE)
                .run(args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            LOGGER.info("** START CLR **");
            LOGGER.info("** END   CLR **");
       };
    }



}

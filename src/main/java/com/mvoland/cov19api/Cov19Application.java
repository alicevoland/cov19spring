package com.mvoland.cov19api;

import com.mvoland.cov19api.business.service.HospDataDatabaseUpdaterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Cov19Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Cov19Application.class);

    public static void main(String[] args) {
        new SpringApplicationBuilder(Cov19Application.class).run(args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            LOGGER.info("** START CLR **");
            HospDataDatabaseUpdaterService hospDataDatabaseUpdaterService = ctx.getBean(HospDataDatabaseUpdaterService.class);
            hospDataDatabaseUpdaterService.update(false);
            LOGGER.info("** END   CLR **");
        };
    }


}

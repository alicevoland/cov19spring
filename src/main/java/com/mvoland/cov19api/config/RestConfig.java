package com.mvoland.cov19api.config;

import com.mvoland.cov19api.hospdata.data.entity.Region;
import com.mvoland.cov19api.hospdata.data.entity.RegionalHospitalisation;
import com.mvoland.cov19api.hospdata.data.entity.RegionalIntensiveCareAdmission;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class RestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(Region.class, RegionalHospitalisation.class, RegionalIntensiveCareAdmission.class);
    }
}
package com.mvoland.cov19api.web.config;

import com.mvoland.cov19api.data.hospdata.entity.Region;
import com.mvoland.cov19api.data.hospdata.entity.RegionalHospitalisation;
import com.mvoland.cov19api.data.hospdata.entity.RegionalIntensiveCareAdmission;
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
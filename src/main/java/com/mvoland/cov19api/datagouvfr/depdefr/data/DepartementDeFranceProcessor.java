package com.mvoland.cov19api.datagouvfr.depdefr.data;

import com.mvoland.cov19api.covidstat.locality.data.Department;
import com.mvoland.cov19api.covidstat.locality.data.Region;
import com.mvoland.cov19api.covidstat.locality.service.LocalityService;
import com.mvoland.cov19api.datagouvfr.common.ValueParser;
import com.mvoland.cov19api.datagouvfr.common.ValueProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepartementDeFranceProcessor implements ValueProcessor<DepartementDeFrance> {

    private final LocalityService localityService;

    @Autowired
    public DepartementDeFranceProcessor(
            LocalityService localityService
    ) {
        this.localityService = localityService;
    }

    @Override
    public void process(DepartementDeFrance value) {
        localityService.safeUpdateRegion(
                new Region(value.getCodeRegion(), value.getNomRegion())
        ).ifPresent(region ->
                localityService.safeUpdateDepartement(
                        new Department(value.getCodeDepartement(), value.getNomDepartement(), region)
                ));
    }
}

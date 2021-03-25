package com.mvoland.cov19api.datagouvfr.hospdata.chir;

import com.mvoland.cov19api.covidstat.hospitalisation.data.entity.RegionalIntensiveCareAdmission;
import com.mvoland.cov19api.covidstat.hospitalisation.service.HospitalisationService;
import com.mvoland.cov19api.covidstat.locality.data.Region;
import com.mvoland.cov19api.covidstat.locality.service.LocalityService;
import com.mvoland.cov19api.datagouvfr.common.ValueProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CovidHospitIncidRegProcessor implements ValueProcessor<CovidHospitIncidReg> {

    private final LocalityService localityService;
    private final HospitalisationService hospitalisationService;

    @Autowired
    public CovidHospitIncidRegProcessor(
            LocalityService localityService,
            HospitalisationService hospitalisationService
    ) {
        this.localityService = localityService;
        this.hospitalisationService = hospitalisationService;
    }

    @Override
    public void process(CovidHospitIncidReg value) {
//        localityService.safeUpdateRegion(new Region(value.getNumReg(), value.getNomReg()));
        localityService
                .findRegionByCode(value.getNumReg())
                .ifPresent(region -> hospitalisationService.safeUpdateRegionalIntensiveCareAdmission(new RegionalIntensiveCareAdmission(
                        region, value.getJour(), value.getIncid_rea())));
    }
}

package com.mvoland.cov19api.datagouvfr.hospdata.dhcac;

import com.mvoland.cov19api.covidstat.hospitalisation.data.entity.RegionalHospitalisation;
import com.mvoland.cov19api.covidstat.hospitalisation.service.HospitalisationService;
import com.mvoland.cov19api.covidstat.locality.service.LocalityService;
import com.mvoland.cov19api.datagouvfr.common.ParsingUtils;
import com.mvoland.cov19api.datagouvfr.common.ValueParser;
import com.mvoland.cov19api.datagouvfr.common.ValueProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DonneesHospitalieresClasseAgeCovid19Processor implements ValueProcessor<DonneesHospitalieresClasseAgeCovid19> {

    private final LocalityService localityService;
    private final HospitalisationService hospitalisationService;

    @Autowired
    public DonneesHospitalieresClasseAgeCovid19Processor(
            LocalityService localityService,
            HospitalisationService hospitalisationService
    ) {
        this.localityService = localityService;
        this.hospitalisationService = hospitalisationService;
    }


    @Override
    public void process(DonneesHospitalieresClasseAgeCovid19 value) {
        localityService
                .findRegionByCode(value.getReg())
                .ifPresent(region -> hospitalisationService.safeUdateRegionalHospitalisation(
                        new RegionalHospitalisation(
                                region,
                                value.getJour(),
                                value.getCl_age90(),
                                value.getHosp(), value.getRea(),
                                value.getRad(), value.getDc()
                        ))
                );
    }
}

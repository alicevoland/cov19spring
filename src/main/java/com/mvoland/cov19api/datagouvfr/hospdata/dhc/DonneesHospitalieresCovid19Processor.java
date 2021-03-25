package com.mvoland.cov19api.datagouvfr.hospdata.dhc;

import com.mvoland.cov19api.covidstat.hospitalisation.service.HospitalisationService;
import com.mvoland.cov19api.covidstat.locality.service.LocalityService;
import com.mvoland.cov19api.datagouvfr.common.ValueProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DonneesHospitalieresCovid19Processor implements ValueProcessor<DonneesHospitalieresCovid19> {

    private final LocalityService localityService;
    private final HospitalisationService hospitalisationService;

    @Autowired
    public DonneesHospitalieresCovid19Processor(
            LocalityService localityService,
            HospitalisationService hospitalisationService
    ) {
        this.localityService = localityService;
        this.hospitalisationService = hospitalisationService;
    }


    @Override
    public void process(DonneesHospitalieresCovid19 value) {

        //TODO: Not implemented yet

    }
}

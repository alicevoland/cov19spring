package com.mvoland.cov19api.datagouvfr.hospdata.service;

import com.mvoland.cov19api.datagouvfr.common.UpdateService;
import com.mvoland.cov19api.datagouvfr.hospdata.chir.CovidHospitIncidRegService;
import com.mvoland.cov19api.datagouvfr.hospdata.dhc.DonneesHospitalieresCovid19Service;
import com.mvoland.cov19api.datagouvfr.hospdata.dhcac.DonneesHospitalieresClasseAgeCovid19Service;
import com.mvoland.cov19api.datagouvfr.hospdata.dhnc.DonneesHospitalieresNouveauxCovid19Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HospDataUpdateService extends UpdateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HospDataUpdateService.class);

    @Autowired
    public HospDataUpdateService(
            CovidHospitIncidRegService covidHospitIncidRegService,
            DonneesHospitalieresClasseAgeCovid19Service donneesHospitalieresClasseAgeCovid19Service,
            DonneesHospitalieresCovid19Service donneesHospitalieresCovid19Service,
            DonneesHospitalieresNouveauxCovid19Service donneesHospitalieresNouveauxCovid19Service
    ) {
        super(
                covidHospitIncidRegService,
                donneesHospitalieresClasseAgeCovid19Service,
                donneesHospitalieresCovid19Service,
                donneesHospitalieresNouveauxCovid19Service
        );
    }
}

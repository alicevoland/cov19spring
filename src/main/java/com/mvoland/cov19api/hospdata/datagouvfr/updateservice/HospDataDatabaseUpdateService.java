package com.mvoland.cov19api.datagouvfr.updateservice;

import com.mvoland.cov19api.business.service.RegionalHospDataService;
import com.mvoland.cov19api.data.entity.Region;
import com.mvoland.cov19api.data.entity.RegionalHospitalisation;
import com.mvoland.cov19api.data.entity.RegionalIntensiveCareAdmission;
import com.mvoland.cov19api.datagouvfr.hospdata.CovidHospitIncidReg;
import com.mvoland.cov19api.datagouvfr.hospdata.DonneesHospitalieresClasseAgeCovid19;
import com.mvoland.cov19api.datagouvfr.hospdata.HospDataProvider;
import com.mvoland.cov19api.utils.PercentCounter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class HospDataDatabaseUpdateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HospDataDatabaseUpdateService.class);

    private final HospDataProvider hospDataProvider;
    private final RegionalHospDataService regionalHospDataService;

    @Autowired
    public HospDataDatabaseUpdateService(
            HospDataProvider hospDataProvider,
            RegionalHospDataService regionalHospDataService
    ) {

        this.hospDataProvider = hospDataProvider;
        this.regionalHospDataService = regionalHospDataService;
    }

    public void processCovidHospitIncidReg(CovidHospitIncidReg covidHospitIncidReg) {
        regionalHospDataService.safeUpdateRegion(new Region(covidHospitIncidReg.getNumReg(), covidHospitIncidReg.getNomReg()));
        regionalHospDataService
                .findRegionByNumber(covidHospitIncidReg.getNumReg())
                .ifPresent(region -> regionalHospDataService.safeUpdateRegionalIntensiveCareAdmission(
                        new RegionalIntensiveCareAdmission(
                                region,
                                covidHospitIncidReg.getJour(),
                                covidHospitIncidReg.getIncid_rea()
                        ))
                );
    }

    public void processCovidHospReg(DonneesHospitalieresClasseAgeCovid19 covidHospClAgeReg) {
        regionalHospDataService
                .findRegionByNumber(covidHospClAgeReg.getReg())
                .ifPresent(region -> regionalHospDataService.safeUdateRegionalHospitalisation(
                        new RegionalHospitalisation(
                                region,
                                covidHospClAgeReg.getJour(),
                                covidHospClAgeReg.getCl_age90(),
                                covidHospClAgeReg.getHosp(), covidHospClAgeReg.getRea(),
                                covidHospClAgeReg.getRad(), covidHospClAgeReg.getDc()
                        ))
                );
    }


    public void update(boolean clean) {
        if (clean) {
            LOGGER.info("Clean databases");
            regionalHospDataService.deleteAllRegionalHospData();
        }

        LOGGER.info("Start populating");

        List<CovidHospitIncidReg> allCovidHospitIncidRegList = hospDataProvider.getAllCovidHospitIncidRegs();
        PercentCounter regIcCounter = new PercentCounter(allCovidHospitIncidRegList.size(), 10, (tick, size, percent) -> {
            LOGGER.info("CovidHospitIncidReg: parsed {} / {}  | {} %", tick, size, percent);
        });
        allCovidHospitIncidRegList.stream().parallel().forEach(
                covidHospitIncidReg -> {
                    processCovidHospitIncidReg(covidHospitIncidReg);
                    regIcCounter.tick();
                }
        );

        List<DonneesHospitalieresClasseAgeCovid19> allCovidHospRegList = hospDataProvider.getAllDonneesHospitalieresClasseAgeCovid19s();
        PercentCounter regHospCounter = new PercentCounter(allCovidHospRegList.size(), 100, (tick, size, percent) -> {
            LOGGER.info("DonneesHospitalieresClasseAgeCovid19: parsed {} / {}  | {} %", tick, size, percent);
        });
        allCovidHospRegList.stream().parallel().forEach(
                covidHospReg -> {
                    processCovidHospReg(covidHospReg);
                    regHospCounter.tick();
                }
        );

    }
}
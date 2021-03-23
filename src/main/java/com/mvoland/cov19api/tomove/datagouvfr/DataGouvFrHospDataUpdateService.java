package com.mvoland.cov19api.tomove.datagouvfr;

import com.mvoland.cov19api.tomove.hospdata.RegionalHospDataService;
import com.mvoland.cov19api.covidstat.locality.data.Region;
import com.mvoland.cov19api.tomove.data.hospdata.entity.RegionalHospitalisation;
import com.mvoland.cov19api.tomove.data.hospdata.entity.RegionalIntensiveCareAdmission;
import com.mvoland.cov19api.tomove.datagouvfr.hospdata.CovidHospitIncidReg;
import com.mvoland.cov19api.tomove.datagouvfr.hospdata.DonneesHospitalieresClasseAgeCovid19;
import com.mvoland.cov19api.tomove.datagouvfr.hospdata.HospDataProvider;
import com.mvoland.cov19api.common.util.PercentCounter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataGouvFrHospDataUpdateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataGouvFrHospDataUpdateService.class);

    private final HospDataProvider hospDataProvider;
    private final RegionalHospDataService regionalHospDataService;

    @Autowired
    public DataGouvFrHospDataUpdateService(
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
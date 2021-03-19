package com.mvoland.cov19api.business.service;

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
public class HospDataDatabaseUpdaterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HospDataDatabaseUpdaterService.class);

    private final HospDataProvider hospDataProvider;
    private final RegionalHospDataService regionalHospDataService;

    @Autowired
    public HospDataDatabaseUpdaterService(
            HospDataProvider hospDataProvider,
            RegionalHospDataService regionalHospDataService
    ) {

        this.hospDataProvider = hospDataProvider;
        this.regionalHospDataService = regionalHospDataService;
    }

    @Transactional
    public void processCovidHospitIncidReg(CovidHospitIncidReg covidHospitIncidReg) {

        Region region = regionalHospDataService.updateIfDifferent(
                new Region(
                        covidHospitIncidReg.getNumReg(),
                        covidHospitIncidReg.getNomReg()
                )
        );
        regionalHospDataService.updateIfDifferent(
                new RegionalIntensiveCareAdmission(
                        region,
                        covidHospitIncidReg.getJour(),
                        covidHospitIncidReg.getIncid_rea()
                )
        );
    }

    @Transactional
    public void processCovidHospReg(DonneesHospitalieresClasseAgeCovid19 covidHospClAgeReg) {

        regionalHospDataService.updateIfDifferent(
                new RegionalHospitalisation(
                        regionalHospDataService.safeFindRegionByNumber(covidHospClAgeReg.getReg()),
                        covidHospClAgeReg.getJour(),
                        covidHospClAgeReg.getCl_age90(),
                        covidHospClAgeReg.getHosp(), covidHospClAgeReg.getRea(),
                        covidHospClAgeReg.getRad(), covidHospClAgeReg.getDc()
                )
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
        PercentCounter regHospCounter = new PercentCounter(allCovidHospRegList.size(), 20, (tick, size, percent) -> {
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
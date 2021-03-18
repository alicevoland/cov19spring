package com.mvoland.cov19api.datagouvfr.hospdata;

import com.mvoland.cov19api.data.entity.Region;
import com.mvoland.cov19api.data.entity.RegionalIntensiveCareAdmission;
import com.mvoland.cov19api.data.repository.RegionalHospitalisationRepository;
import com.mvoland.cov19api.business.service.RegionService;
import com.mvoland.cov19api.business.service.RegionalService;
import com.mvoland.cov19api.utils.PercentCounter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class HospDataDatabaseUpdater {

    private static final Logger LOGGER = LoggerFactory.getLogger(HospDataDatabaseUpdater.class);

    private final HospDataProvider hospDataProvider;

    private final RegionService regionService;
    private final RegionalService regionalService;
    private final RegionalHospitalisationRepository regionalHospitalisationRepository;

    @Autowired
    public HospDataDatabaseUpdater(
            HospDataProvider hospDataProvider,
            RegionService regionService,
            RegionalService regionalService,
            RegionalHospitalisationRepository regionalHospitalisationRepository
    ) {

        this.hospDataProvider = hospDataProvider;
        this.regionService = regionService;
        this.regionalService = regionalService;
        this.regionalHospitalisationRepository = regionalHospitalisationRepository;
    }

    @Transactional
    void processCovidHospitIncidReg(CovidHospitIncidReg covidHospitIncidReg) {

        Region region = regionService.updateIfDifferent(
                new Region(
                        covidHospitIncidReg.getNumReg(),
                        covidHospitIncidReg.getNomReg()));

        regionalService.updateIfDifferent(
                new RegionalIntensiveCareAdmission(
                        region,
                        LocalDate.parse(covidHospitIncidReg.getJour(), DateTimeFormatter.ISO_LOCAL_DATE),
                        covidHospitIncidReg.getIncid_rea()));
    }

    public void update(boolean clean) {
        if (clean) {
            LOGGER.info("Clean databases");

            regionalService.deleteAllRegionalAdmissions();
        regionService.deleteAllRegions();
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

//        List<DonneesHospitalieresClasseAgeCovid19> allDonneesHospitalieresClasseAgeCovid19s = hospDataProvider.getAllDonneesHospitalieresClasseAgeCovid19s();
//        PercentCounter regHospCounter = new PercentCounter(allDonneesHospitalieresClasseAgeCovid19s.size(), 10, (tick, size, percent) -> {
//            LOGGER.info("CovidHospitIncidReg: parsed {} / {}  | {} %", tick, size, percent);
//        });
//        allDonneesHospitalieresClasseAgeCovid19s.forEach(
//                hospClAge -> {
//
//                    Integer regionNumber = Integer.parseInt(hospClAge.getReg());
//                    LocalDate noticeDate = LocalDate.parse(hospClAge.getJour(), DateTimeFormatter.ISO_LOCAL_DATE);
//                    AgeGroup ageGroup = AgeGroup.fromValue(Integer.parseInt(hospClAge.getCl_age90()));
//                    Integer hosp = hospClAge.getHosp();
//                    Integer rea = hospClAge.getRea();
//                    Integer rad = hospClAge.getRad();
//                    Integer dc = hospClAge.getDc();
//
//                    Region region;
//                    Optional<Region> regionByNumber = regionRepository.findByRegionNumber(regionNumber);
//
//                    // Insert
//                    if (regionByNumber.isEmpty()) {
//                        LOGGER.warn("Region {} does not exist, creating fake one", regionNumber);
//                        region = regionRepository.save(new Region(regionNumber, "Unknown"));
//                    } else {
//                        region = regionByNumber.get();
//                    }
//
//                    // Insert only
//                    RegionalHospitalisation regionalHospitalisation = regionalHospitalisationRepository.save(
//                            new RegionalHospitalisation(region, noticeDate, ageGroup,
//                                    hosp, rea, rad, dc));
//
//                    regHospCounter.tick();
//                }
//
//        );
    }
}
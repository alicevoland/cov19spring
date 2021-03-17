package com.mvoland.cov19api.datagouvfr;

import com.mvoland.cov19api.data.entity.AgeGroup;
import com.mvoland.cov19api.data.entity.Region;
import com.mvoland.cov19api.data.entity.RegionalHospitalisation;
import com.mvoland.cov19api.data.entity.RegionalIntensiveCareAdmission;
import com.mvoland.cov19api.data.repository.RegionRepository;
import com.mvoland.cov19api.data.repository.RegionalHospitalisationRepository;
import com.mvoland.cov19api.data.repository.RegionalIntensiveCareAdmissionRepository;
import com.mvoland.cov19api.datagouvfr.hospdata.CovidHospitIncidReg;
import com.mvoland.cov19api.datagouvfr.hospdata.DonneesHospitalieresClasseAgeCovid19;
import com.mvoland.cov19api.datagouvfr.hospdata.HospDataProvider;
import com.mvoland.cov19api.utils.PercentCounter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Component
public class HospDataDatabaseUpdater {

    private static final Logger LOGGER = LoggerFactory.getLogger(HospDataDatabaseUpdater.class);

    private final HospDataProvider hospDataProvider;
    private final RegionRepository regionRepository;
    private final RegionalIntensiveCareAdmissionRepository regionalIntensiveCareAdmissionRepository;
    private final RegionalHospitalisationRepository regionalHospitalisationRepository;

    @Autowired
    public HospDataDatabaseUpdater(
            HospDataProvider hospDataProvider,
            RegionRepository regionRepository,
            RegionalIntensiveCareAdmissionRepository regionalIntensiveCareAdmissionRepository,
            RegionalHospitalisationRepository regionalHospitalisationRepository
    ) {

        this.hospDataProvider = hospDataProvider;
        this.regionRepository = regionRepository;
        this.regionalIntensiveCareAdmissionRepository = regionalIntensiveCareAdmissionRepository;
        this.regionalHospitalisationRepository = regionalHospitalisationRepository;
    }

    @Transactional
    public void freshUpdate() {
        LOGGER.info("Clean databases");

        regionRepository.deleteAll();
        regionalIntensiveCareAdmissionRepository.deleteAll();

        LOGGER.info("Start populating");

        List<CovidHospitIncidReg> allCovidHospitIncidRegList = hospDataProvider.getAllCovidHospitIncidRegs();
        PercentCounter regIcCounter = new PercentCounter(allCovidHospitIncidRegList.size(), 10, (tick, size, percent) -> {
            LOGGER.info("CovidHospitIncidReg: parsed {} / {}  | {} %", tick, size, percent);
        });
        allCovidHospitIncidRegList.forEach(
                covidHospitIncidReg -> {

                    String regionName = covidHospitIncidReg.getNomReg();
                    Integer regionNumber = covidHospitIncidReg.getNumReg();
                    Integer icAdmissions = covidHospitIncidReg.getIncid_rea();
                    LocalDate noticeDate = LocalDate.parse(covidHospitIncidReg.getJour(), DateTimeFormatter.ISO_LOCAL_DATE);

                    Region region;
                    Optional<Region> regionByNumber = regionRepository.findByRegionNumber(regionNumber);

                    // Insert
                    if (regionByNumber.isEmpty()) {
                        region = regionRepository.save(new Region(regionNumber, regionName));
                    } else {
                        region = regionByNumber.get();
                        // Update
                        if (!region.getRegionName().equals(regionName)) {
                            LOGGER.warn("Region {} previous name {} will be {}", regionNumber, region.getRegionName(), regionName);
                            region.setRegionName(regionName);
                            region = regionRepository.save(region);
                        }
                    }

                    // Insert only
                    RegionalIntensiveCareAdmission regionalIntensiveCareAdmission = regionalIntensiveCareAdmissionRepository.save(new RegionalIntensiveCareAdmission(region, noticeDate, icAdmissions));

                    regIcCounter.tick();
                }

        );
        List<DonneesHospitalieresClasseAgeCovid19> allDonneesHospitalieresClasseAgeCovid19s = hospDataProvider.getAllDonneesHospitalieresClasseAgeCovid19s();
        PercentCounter regHospCounter = new PercentCounter(allDonneesHospitalieresClasseAgeCovid19s.size(), 10, (tick, size, percent) -> {
            LOGGER.info("CovidHospitIncidReg: parsed {} / {}  | {} %", tick, size, percent);
        });
        allDonneesHospitalieresClasseAgeCovid19s.forEach(
                hospClAge -> {

                    Integer regionNumber = Integer.parseInt(hospClAge.getReg());
                    LocalDate noticeDate = LocalDate.parse(hospClAge.getJour(), DateTimeFormatter.ISO_LOCAL_DATE);
                    AgeGroup ageGroup = AgeGroup.fromValue(Integer.parseInt(hospClAge.getCl_age90()));
                    Integer hosp = hospClAge.getHosp();
                    Integer rea = hospClAge.getRea();
                    Integer rad = hospClAge.getRad();
                    Integer dc = hospClAge.getDc();

                    Region region;
                    Optional<Region> regionByNumber = regionRepository.findByRegionNumber(regionNumber);

                    // Insert
                    if (regionByNumber.isEmpty()) {
                        LOGGER.warn("Region {} does not exist, creating fake one", regionNumber);
                        region = regionRepository.save(new Region(regionNumber, "Unknown"));
                    } else {
                        region = regionByNumber.get();
                    }

                    // Insert only
                    RegionalHospitalisation regionalHospitalisation = regionalHospitalisationRepository.save(
                            new RegionalHospitalisation(region, noticeDate, ageGroup,
                                    hosp, rea, rad, dc));

                    regHospCounter.tick();
                }

        );
    }
}
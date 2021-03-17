package com.mvoland.cov19api.datagouvfr.hospdata;

import com.mvoland.cov19api.data.entity.Region;
import com.mvoland.cov19api.data.entity.RegionalIntensiveCareAdmission;
import com.mvoland.cov19api.data.repository.RegionRepository;
import com.mvoland.cov19api.data.repository.RegionalIntensiveCareAdmissionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class HospDataDatabaseUpdater {

    private static final Logger LOGGER = LoggerFactory.getLogger(HospDataDatabaseUpdater.class);

    private final HospDataProvider hospDataProvider;
    private final RegionRepository regionRepository;
    private final RegionalIntensiveCareAdmissionRepository regionalIntensiveCareAdmissionRepository;

    @Autowired
    public HospDataDatabaseUpdater(
            HospDataProvider hospDataProvider,
            RegionRepository regionRepository,
            RegionalIntensiveCareAdmissionRepository regionalIntensiveCareAdmissionRepository
    ) {

        this.hospDataProvider = hospDataProvider;
        this.regionRepository = regionRepository;
        this.regionalIntensiveCareAdmissionRepository = regionalIntensiveCareAdmissionRepository;
    }

    @Transactional
    public void freshUpdate() {
        AtomicInteger regionalIntensiveCareAdmissionAddition = new AtomicInteger();
        AtomicInteger regionAddition = new AtomicInteger();

        LOGGER.info("Clean databases");
        regionRepository.deleteAll();
        regionalIntensiveCareAdmissionRepository.deleteAll();

        LOGGER.info("Start populating");
        List<CovidHospitIncidReg> allCovidHospitIncidRegList = hospDataProvider.getAllCovidHospitIncidRegs();
        allCovidHospitIncidRegList.forEach(
                covidHospitIncidReg -> {

                    String regionName = covidHospitIncidReg.getNomReg();
                    Integer regionNumber = covidHospitIncidReg.getNumReg();
                    Integer icAdmissions = covidHospitIncidReg.getIncid_rea();
                    LocalDate noticeDate = LocalDate.parse(covidHospitIncidReg.getJour(), DateTimeFormatter.ISO_LOCAL_DATE);

                    Region region = regionRepository.findByRegionNumber(regionNumber);
                    if (region == null) {
                        region = new Region(regionNumber, regionName);
                        regionRepository.save(region);
                        regionAddition.incrementAndGet();
                    } else if (!region.getRegionName().equals(regionName)) {
                        LOGGER.warn("Region {} previous name {} will be {}", regionNumber, region.getRegionName(), regionName);
                        region.setRegionName(regionName);
                        regionRepository.save(region);
                    }

                    RegionalIntensiveCareAdmission regionalIntensiveCareAdmission = null;//regionalIntensiveCareAdmissionRepository.findByRegionAndNoticeDate(region, noticeDate);
                    if (regionalIntensiveCareAdmission == null) {
                        regionalIntensiveCareAdmission = new RegionalIntensiveCareAdmission(region, noticeDate, icAdmissions);
                        regionalIntensiveCareAdmissionRepository.save(regionalIntensiveCareAdmission);
                        regionalIntensiveCareAdmissionAddition.incrementAndGet();
                    } else if (!regionalIntensiveCareAdmission.getIntensiveCareAdmissionCount().equals(icAdmissions)) {
                        LOGGER.warn("regionalIntensiveCareAdmission {} update admission count from {} to {}", regionalIntensiveCareAdmission, regionalIntensiveCareAdmission.getIntensiveCareAdmissionCount(), icAdmissions);
                        regionalIntensiveCareAdmission.setIntensiveCareAdmissionCount(icAdmissions);
                        regionalIntensiveCareAdmissionRepository.save(regionalIntensiveCareAdmission);
                    }

                    if (regionalIntensiveCareAdmissionAddition.get() % 100 == 0) {

                        LOGGER.info("Added {} regionAddition", regionAddition.get());
                        LOGGER.info("Added {} / {} regionalIntensiveCareAdmission", regionalIntensiveCareAdmissionAddition.get(), allCovidHospitIncidRegList.size());

                    }
                }

        );
        LOGGER.info("Added {} regionAddition", regionAddition.get());
        LOGGER.info("Added {} regionalIntensiveCareAdmission", regionalIntensiveCareAdmissionAddition.get());
    }
}
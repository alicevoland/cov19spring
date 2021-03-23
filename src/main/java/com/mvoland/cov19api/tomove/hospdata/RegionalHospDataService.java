package com.mvoland.cov19api.tomove.hospdata;

import com.mvoland.cov19api.covidstat.locality.data.Region;
import com.mvoland.cov19api.tomove.data.hospdata.entity.RegionalHospitalisation;
import com.mvoland.cov19api.tomove.data.hospdata.entity.RegionalIntensiveCareAdmission;
import com.mvoland.cov19api.covidstat.locality.data.RegionRepository;
import com.mvoland.cov19api.tomove.data.hospdata.repository.RegionalHospitalisationRepository;
import com.mvoland.cov19api.tomove.data.hospdata.repository.RegionalIntensiveCareAdmissionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegionalHospDataService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegionalHospDataService.class);

    private final RegionRepository regionRepository;
    private final RegionalIntensiveCareAdmissionRepository regionalAdmissionsRepository;
    private final RegionalHospitalisationRepository regionalHospitalisationRepository;

    @Autowired
    public RegionalHospDataService(RegionRepository regionRepository,
                                   RegionalIntensiveCareAdmissionRepository regionalAdmissionsRepository,
                                   RegionalHospitalisationRepository regionalHospitalisationRepository) {
        this.regionRepository = regionRepository;
        this.regionalAdmissionsRepository = regionalAdmissionsRepository;
        this.regionalHospitalisationRepository = regionalHospitalisationRepository;
    }

    public Optional<Region> findRegionByNumber(Integer regionNumber) {
        return regionRepository.findByRegionNumber(regionNumber);
    }

    @Transactional
    public Region updateRegion(Region region) {
        return regionRepository.findByRegionNumber(region.getRegionNumber())
                .map(existingRegion -> {
                    if (!existingRegion.equals(region)) {
                        existingRegion.setRegionName(region.getRegionName());
                    }
                    return regionRepository.save(existingRegion);
                })
                .orElseGet(() -> regionRepository.save(region)
                );
    }

    public Optional<Region> safeUpdateRegion(Region region) {
        try {
            return Optional.of(updateRegion(region));
        } catch (Exception e) {
            LOGGER.warn("Could not update region {}", region);
            return Optional.empty();
        }
    }

    @Transactional
    public RegionalIntensiveCareAdmission updateRegionalIntensiveCareAdmission(RegionalIntensiveCareAdmission admission) {
        return regionalAdmissionsRepository.findByRegionAndNoticeDate(admission.getRegion(), admission.getNoticeDate())
                .map(existingAdmission -> {
                    if (existingAdmission.equals(admission)) {
                        return existingAdmission;
                    } else {
                        existingAdmission.setIntensiveCareAdmissionCount(admission.getIntensiveCareAdmissionCount());
                        return regionalAdmissionsRepository.save(existingAdmission);
                    }
                })
                .orElseGet(() -> regionalAdmissionsRepository.save(admission)
                );
    }

    public Optional<RegionalIntensiveCareAdmission> safeUpdateRegionalIntensiveCareAdmission(RegionalIntensiveCareAdmission admission) {
        try {
            return Optional.of(updateRegionalIntensiveCareAdmission(admission));
        } catch (Exception e) {
            LOGGER.warn("Could not update {}", admission);
            return Optional.empty();
        }
    }

    @Transactional
    public RegionalHospitalisation updateRegionalHospitalisation(RegionalHospitalisation hospitalisation) {
        return regionalHospitalisationRepository.findByRegionAndNoticeDateAndAgeGroup(hospitalisation.getRegion(), hospitalisation.getNoticeDate(), hospitalisation.getAgeGroup())
                .map(existingHospitalisation -> {
                    if (existingHospitalisation.equals(hospitalisation)) {
                        return existingHospitalisation;
                    } else {
                        existingHospitalisation.setCurrentHospitalizedCount(hospitalisation.getCurrentHospitalizedCount());
                        existingHospitalisation.setCurrentIntensiveCareCount((hospitalisation.getCurrentIntensiveCareCount()));
                        existingHospitalisation.setCurrentRadiationCount(hospitalisation.getCurrentRadiationCount());
                        existingHospitalisation.setCurrentDeathCount(hospitalisation.getCurrentDeathCount());
                        return regionalHospitalisationRepository.save(existingHospitalisation);
                    }
                })
                .orElseGet(() -> regionalHospitalisationRepository.save(hospitalisation)
                );
    }

    public Optional<RegionalHospitalisation> safeUdateRegionalHospitalisation(RegionalHospitalisation hospitalisation) {
        try {
            return Optional.of(updateRegionalHospitalisation(hospitalisation));
        } catch (Exception e) {
            LOGGER.warn("Could not update {}", hospitalisation);
            return Optional.empty();
        }
    }

    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }


    public List<RegionalIntensiveCareAdmission> getAllRegionalICAdmissions() {
        return regionalAdmissionsRepository.findAll();
    }

    public List<RegionalHospitalisation> getAllRegionalHospitalisations() {
        return regionalHospitalisationRepository.findAll();
    }

    public List<RegionalIntensiveCareAdmission> getByRegionNumberAndDays(
            Integer regionNumber, Integer days
    ) {
        LocalDate date = LocalDate.now().minusDays(days);
        return regionRepository
                .findByRegionNumber(regionNumber)
                .map(region -> regionalAdmissionsRepository
                        .findByRegion(region).stream()
                        .filter(admission -> admission.getNoticeDate().isAfter(date))
                        .collect(Collectors.toList())).orElse(Collections.emptyList());
    }


    public synchronized void deleteAllRegionalHospData() {
        regionalHospitalisationRepository.deleteAll();
        regionalAdmissionsRepository.deleteAll();
        regionRepository.deleteAll();
    }

}

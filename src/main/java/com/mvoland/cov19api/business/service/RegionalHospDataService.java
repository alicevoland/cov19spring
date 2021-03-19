package com.mvoland.cov19api.business.service;

import com.mvoland.cov19api.data.entity.Region;
import com.mvoland.cov19api.data.entity.RegionalHospitalisation;
import com.mvoland.cov19api.data.entity.RegionalIntensiveCareAdmission;
import com.mvoland.cov19api.data.repository.RegionRepository;
import com.mvoland.cov19api.data.repository.RegionalHospitalisationRepository;
import com.mvoland.cov19api.data.repository.RegionalIntensiveCareAdmissionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


    private synchronized Region getOrInsert(Region region, boolean update) {
        Optional<Region> existingRegion = regionRepository.findByRegionNumber(region.getRegionNumber());
        if (existingRegion.isPresent()) {
            if (!update || existingRegion.get().getRegionName().equals(region.getRegionName())) {
                return existingRegion.get();
            } else {
                LOGGER.warn("Change region {} to {}", existingRegion, region);
                return regionRepository.save(region);
            }
        } else {
            return regionRepository.save(region);
        }
    }


    private synchronized RegionalIntensiveCareAdmission getOrInsert(RegionalIntensiveCareAdmission regionalAdmission, boolean update) {
        Optional<RegionalIntensiveCareAdmission> existingRegionalAdmission =
                regionalAdmissionsRepository.findByRegionAndNoticeDate(regionalAdmission.getRegion(), regionalAdmission.getNoticeDate());
        if (existingRegionalAdmission.isPresent()) {
            if (!update || existingRegionalAdmission.get().equals(regionalAdmission)) {
                return existingRegionalAdmission.get();
            } else {
                LOGGER.warn("Change from {} to {}", existingRegionalAdmission.get(), regionalAdmission);
                return regionalAdmissionsRepository.save(regionalAdmission);
            }
        } else {
            return regionalAdmissionsRepository.save(regionalAdmission);
        }
    }

    private synchronized RegionalHospitalisation getOrInsert(RegionalHospitalisation regionalHospitalisation, boolean update) {
        Optional<RegionalHospitalisation> existingRegionalHospitalisation =
                regionalHospitalisationRepository.findByRegionAndNoticeDateAndAgeGroup(
                        regionalHospitalisation.getRegion(), regionalHospitalisation.getNoticeDate(), regionalHospitalisation.getAgeGroup());
        if (existingRegionalHospitalisation.isPresent()) {
            if (!update || existingRegionalHospitalisation.get().equals(regionalHospitalisation)) {
                return existingRegionalHospitalisation.get();
            } else {
                LOGGER.warn("Change from {} to {}", existingRegionalHospitalisation.get(), regionalHospitalisation);
                return regionalHospitalisationRepository.save(regionalHospitalisation);
            }
        } else {
            return regionalHospitalisationRepository.save(regionalHospitalisation);
        }
    }


    /**
     * Find a Region by regionNumber, or put an unknown region in database.
     *
     * @param regionNumber
     * @return Existing Region in database, or newly created 'Region(regionNumber, "Unknown")
     */
    public Region safeFindRegionByNumber(Integer regionNumber) {
        return regionRepository
                .findByRegionNumber(regionNumber)
                .orElseGet(() -> getOrInsert(new Region(regionNumber, "Unknown"), false));
    }

    public Region updateIfDifferent(Region region) {
        Optional<Region> existingRegion = regionRepository.findByRegionNumber(region.getRegionNumber());

        if (existingRegion.isEmpty() || !existingRegion.get().equals(region)) {
            return getOrInsert(region, true);

        } else {
            return existingRegion.get();
        }
    }


    public RegionalIntensiveCareAdmission updateIfDifferent(RegionalIntensiveCareAdmission regionalAdmission) {
        Optional<RegionalIntensiveCareAdmission> existingAdmission =
                regionalAdmissionsRepository.findByRegionAndNoticeDate(regionalAdmission.getRegion(), regionalAdmission.getNoticeDate());

        if (existingAdmission.isEmpty() || !existingAdmission.get().equals(regionalAdmission)) {
            return getOrInsert(regionalAdmission, true);

        } else {
            return existingAdmission.get();
        }
    }

    public RegionalHospitalisation updateIfDifferent(RegionalHospitalisation regionalHospitalisation) {
        Optional<RegionalHospitalisation> existingHospitalisation =
                regionalHospitalisationRepository.findByRegionAndNoticeDateAndAgeGroup(
                        regionalHospitalisation.getRegion(), regionalHospitalisation.getNoticeDate(), regionalHospitalisation.getAgeGroup());

        if (existingHospitalisation.isEmpty() || !existingHospitalisation.get().equals(regionalHospitalisation)) {
            return getOrInsert(regionalHospitalisation, true);

        } else {
            return existingHospitalisation.get();
        }
    }

    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }


    public List<RegionalIntensiveCareAdmission> getAllAdmissions() {
        return regionalAdmissionsRepository.findAll();
    }

    public List<RegionalHospitalisation> getAllRegionalHospitalisations() {
        return regionalHospitalisationRepository.findAll();
    }


    public synchronized void deleteAllRegionalHospData() {
        regionalHospitalisationRepository.deleteAll();
        regionalAdmissionsRepository.deleteAll();
        regionRepository.deleteAll();
    }

}

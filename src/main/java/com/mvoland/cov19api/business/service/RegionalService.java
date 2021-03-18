package com.mvoland.cov19api.business.service;

import com.mvoland.cov19api.data.entity.Region;
import com.mvoland.cov19api.data.entity.RegionalIntensiveCareAdmission;
import com.mvoland.cov19api.data.repository.RegionalIntensiveCareAdmissionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionalService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegionalService.class);

    private final RegionalIntensiveCareAdmissionRepository regionalAdmissionsRepository;

    @Autowired
    public RegionalService(RegionalIntensiveCareAdmissionRepository regionalAdmissionsRepository) {

        this.regionalAdmissionsRepository = regionalAdmissionsRepository;
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

    public RegionalIntensiveCareAdmission updateIfDifferent(RegionalIntensiveCareAdmission regionalAdmission) {
        Optional<RegionalIntensiveCareAdmission> existingAdmission =
                regionalAdmissionsRepository.findByRegionAndNoticeDate(regionalAdmission.getRegion(), regionalAdmission.getNoticeDate());

        if (existingAdmission.isEmpty() || !existingAdmission.get().equals(regionalAdmission)) {
            return getOrInsert(regionalAdmission, true);

        } else {
            return existingAdmission.get();
        }
    }

    public List<RegionalIntensiveCareAdmission> getAllAdmissions() {
        return regionalAdmissionsRepository.findAll();
    }

    public synchronized void deleteAllRegionalAdmissions() {
        regionalAdmissionsRepository.deleteAll();
    }

}

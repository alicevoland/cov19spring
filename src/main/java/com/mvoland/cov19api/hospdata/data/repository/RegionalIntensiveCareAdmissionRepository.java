package com.mvoland.cov19api.data.repository;

import com.mvoland.cov19api.data.entity.Region;
import com.mvoland.cov19api.data.entity.RegionalIntensiveCareAdmission;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RegionalIntensiveCareAdmissionRepository extends CrudRepository<RegionalIntensiveCareAdmission, Long> {

    Optional<RegionalIntensiveCareAdmission> findByRegionAndNoticeDate(Region region, LocalDate noticeDate);

    @Override
    List<RegionalIntensiveCareAdmission> findAll();
}

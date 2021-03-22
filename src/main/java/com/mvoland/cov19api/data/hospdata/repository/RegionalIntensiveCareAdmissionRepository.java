package com.mvoland.cov19api.data.hospdata.repository;

import com.mvoland.cov19api.data.hospdata.entity.Region;
import com.mvoland.cov19api.data.hospdata.entity.RegionalIntensiveCareAdmission;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RegionalIntensiveCareAdmissionRepository extends CrudRepository<RegionalIntensiveCareAdmission, Long> {

    Optional<RegionalIntensiveCareAdmission> findByRegionAndNoticeDate(Region region, LocalDate noticeDate);

    @Override
    List<RegionalIntensiveCareAdmission> findAll();

    List<RegionalIntensiveCareAdmission> findByNoticeDateAfter(LocalDate noticeDate);

    List<RegionalIntensiveCareAdmission> findByRegion(Region region);

    List<RegionalIntensiveCareAdmission> findByRegionAndNoticeDateAfter(Region region, LocalDate noticeDate);
}

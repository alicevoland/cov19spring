package com.mvoland.cov19api.covidstat.hospitalisation.data.repository;

import com.mvoland.cov19api.covidstat.hospitalisation.data.entity.RegionalIntensiveCareAdmission;
import com.mvoland.cov19api.covidstat.locality.data.Region;
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

    List<RegionalIntensiveCareAdmission> findByRegionAndNoticeDateIsBetween(
            Region region,
            LocalDate noticeDateBegin,
            LocalDate noticeDateEnd
    );
}

package com.mvoland.cov19api.hospdata.data.repository;

import com.mvoland.cov19api.hospdata.data.types.AgeGroup;
import com.mvoland.cov19api.hospdata.data.entity.Region;
import com.mvoland.cov19api.hospdata.data.entity.RegionalHospitalisation;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RegionalHospitalisationRepository extends CrudRepository<RegionalHospitalisation, Long> {
    Optional<RegionalHospitalisation> findByRegionAndNoticeDateAndAgeGroup(Region region, LocalDate noticeDate, AgeGroup ageGroup);

    @Override
    List<RegionalHospitalisation> findAll();
}

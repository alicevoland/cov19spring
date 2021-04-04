package com.mvoland.cov19api.covidstat.hospitalisation.data.repository;

import com.mvoland.cov19api.common.type.AgeGroup;
import com.mvoland.cov19api.covidstat.hospitalisation.data.entity.RegionalHospitalisation;
import com.mvoland.cov19api.covidstat.locality.data.entity.Region;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RegionalHospitalisationRepository extends CrudRepository<RegionalHospitalisation, Long> {
    Optional<RegionalHospitalisation> findByRegionAndNoticeDateAndAgeGroup(Region region, LocalDate noticeDate, AgeGroup ageGroup);


    @Override
    @NonNull
    List<RegionalHospitalisation> findAll();
}

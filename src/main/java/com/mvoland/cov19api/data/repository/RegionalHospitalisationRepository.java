package com.mvoland.cov19api.data.repository;

import com.mvoland.cov19api.data.entity.RegionalHospitalisation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RegionalHospitalisationRepository extends CrudRepository<RegionalHospitalisation, Long> {
    @Override
    List<RegionalHospitalisation> findAll();
}

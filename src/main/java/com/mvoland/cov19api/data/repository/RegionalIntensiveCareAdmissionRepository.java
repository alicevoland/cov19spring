package com.mvoland.cov19api.data.repository;

import com.mvoland.cov19api.data.entity.Region;
import com.mvoland.cov19api.data.entity.RegionalIntensiveCareAdmission;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface RegionalIntensiveCareAdmissionRepository extends CrudRepository<RegionalIntensiveCareAdmission, Long> {

}

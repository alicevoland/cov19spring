package com.mvoland.cov19api.covidstat.hospitalisation.data.repository;

import com.mvoland.cov19api.covidstat.hospitalisation.data.entity.DepartmentalHospitalisation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface DepartmentalHospitalisationRepository extends CrudRepository<DepartmentalHospitalisation, Long> {
    @Override
    @NonNull
    List<DepartmentalHospitalisation> findAll();
}

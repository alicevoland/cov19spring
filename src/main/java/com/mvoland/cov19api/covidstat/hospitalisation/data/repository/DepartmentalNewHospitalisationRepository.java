package com.mvoland.cov19api.covidstat.hospitalisation.data.repository;

import com.mvoland.cov19api.covidstat.hospitalisation.data.entity.DepartmentalNewHospitalisation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface DepartmentalNewHospitalisationRepository extends CrudRepository<DepartmentalNewHospitalisation, Long> {
    @Override
    @NonNull
    List<DepartmentalNewHospitalisation> findAll();
}

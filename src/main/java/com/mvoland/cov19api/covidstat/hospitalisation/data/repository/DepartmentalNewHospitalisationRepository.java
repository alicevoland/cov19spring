package com.mvoland.cov19api.covidstat.hospitalisation.data.repository;

import com.mvoland.cov19api.covidstat.hospitalisation.data.entity.DepartmentalNewHospitalisation;
import com.mvoland.cov19api.covidstat.locality.data.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DepartmentalNewHospitalisationRepository extends CrudRepository<DepartmentalNewHospitalisation, Long> {
    @Override
    @NonNull
    List<DepartmentalNewHospitalisation> findAll();

    Optional<DepartmentalNewHospitalisation> findByDepartmentAndNoticeDate(Department departement, LocalDate noticeDate);
}

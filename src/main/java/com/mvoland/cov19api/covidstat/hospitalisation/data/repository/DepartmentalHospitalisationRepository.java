package com.mvoland.cov19api.covidstat.hospitalisation.data.repository;

import com.mvoland.cov19api.common.type.Sex;
import com.mvoland.cov19api.covidstat.hospitalisation.data.entity.DepartmentalHospitalisation;
import com.mvoland.cov19api.covidstat.locality.data.entity.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DepartmentalHospitalisationRepository extends CrudRepository<DepartmentalHospitalisation, Long> {
    @Override
    @NonNull
    List<DepartmentalHospitalisation> findAll();

    Optional<DepartmentalHospitalisation> findByDepartmentAndNoticeDateAndSex(Department departement, LocalDate noticeDate, Sex sex);
}

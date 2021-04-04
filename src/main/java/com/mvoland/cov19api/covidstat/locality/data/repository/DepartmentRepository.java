package com.mvoland.cov19api.covidstat.locality.data.repository;

import com.mvoland.cov19api.covidstat.locality.data.entity.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {

    Optional<Department> findByDepartmentCode(String departmentCode);

    List<Department> findAllByDepartmentNameContaining(String departmentName);

    @Override
    @NonNull
    List<Department> findAll();
}

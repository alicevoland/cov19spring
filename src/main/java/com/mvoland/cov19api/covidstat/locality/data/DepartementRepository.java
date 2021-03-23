package com.mvoland.cov19api.covidstat.locality.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartementRepository extends CrudRepository<Departement, Long> {

    Optional<Departement> findByDepartementNumber(Integer departementNumber);
}

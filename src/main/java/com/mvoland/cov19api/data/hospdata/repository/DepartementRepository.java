package com.mvoland.cov19api.data.hospdata.repository;

import com.mvoland.cov19api.data.hospdata.entity.Departement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartementRepository extends CrudRepository<Departement, Long> {

    Optional<Departement> findByDepartementNumber(Integer departementNumber);
}

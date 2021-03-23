package com.mvoland.cov19api.covidstat.locality.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegionRepository extends CrudRepository<Region, Long> {

    Optional<Region> findByRegionNumber(Integer regionNumber);
    List<Region> findAll();
}

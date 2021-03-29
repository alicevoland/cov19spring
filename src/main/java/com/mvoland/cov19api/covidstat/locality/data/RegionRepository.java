package com.mvoland.cov19api.covidstat.locality.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegionRepository extends CrudRepository<Region, Long> {

    Optional<Region> findByRegionCode(String regionCode);

    List<Region> findAllByRegionNameContaining(String regionName);

    @Override
    @NonNull
    List<Region> findAll();
}

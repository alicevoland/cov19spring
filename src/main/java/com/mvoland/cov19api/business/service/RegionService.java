package com.mvoland.cov19api.business.service;

import com.mvoland.cov19api.data.entity.Region;
import com.mvoland.cov19api.data.repository.RegionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegionService.class);

    private final RegionRepository regionRepository;

    @Autowired
    public RegionService(RegionRepository regionRepository) {

        this.regionRepository = regionRepository;
    }

    private synchronized Region getOrInsert(Region region, boolean update) {
        Optional<Region> existingRegion = regionRepository.findByRegionNumber(region.getRegionNumber());
        if (existingRegion.isPresent()) {
            if (!update || existingRegion.get().getRegionName().equals(region.getRegionName())) {
                return existingRegion.get();
            } else {
                LOGGER.warn("Change region {} to {}", existingRegion, region);
                return regionRepository.save(region);
            }
        } else {
            return regionRepository.save(region);
        }
    }

    public Region safeFindByNumber(Integer regionNumber) {
        return regionRepository
                .findByRegionNumber(regionNumber)
                .orElseGet(() -> getOrInsert(new Region(regionNumber, "Unknown"), false));
    }


    public Region updateIfDifferent(Region region) {
        Optional<Region> existingRegion = regionRepository.findByRegionNumber(region.getRegionNumber());

        if (existingRegion.isEmpty() || !existingRegion.get().equals(region)) {
            return getOrInsert(region, true);

        } else {
            return existingRegion.get();
        }
    }

    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }


    public synchronized void deleteAllRegions() {
        regionRepository.deleteAll();
    }
}

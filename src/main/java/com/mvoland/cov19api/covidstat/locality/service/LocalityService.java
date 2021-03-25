package com.mvoland.cov19api.covidstat.locality.service;

import com.mvoland.cov19api.covidstat.hospitalisation.service.HospitalisationService;
import com.mvoland.cov19api.covidstat.locality.data.Department;
import com.mvoland.cov19api.covidstat.locality.data.DepartmentRepository;
import com.mvoland.cov19api.covidstat.locality.data.Region;
import com.mvoland.cov19api.covidstat.locality.data.RegionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LocalityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HospitalisationService.class);

    private final RegionRepository regionRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public LocalityService(
            RegionRepository regionRepository,
            DepartmentRepository departmentRepository
    ) {
        this.regionRepository = regionRepository;
        this.departmentRepository = departmentRepository;
    }

    public Optional<Region> findRegionByNumber(Integer regionNumber) {
        return regionRepository.findByRegionNumber(regionNumber);
    }

    public Optional<Department> findDepartmentByNumber(Integer departmentNumber) {
        return departmentRepository.findByDepartmentNumber(departmentNumber);
    }

    @Transactional
    public Region updateRegion(Region region) {
        return regionRepository.findByRegionNumber(region.getRegionNumber())
                .map(existingRegion -> {
                    if (!existingRegion.equals(region)) {
                        existingRegion.setRegionName(region.getRegionName());
                    }
                    return regionRepository.save(existingRegion);
                })
                .orElseGet(() -> regionRepository.save(region)
                );
    }

    @Transactional
    public Department updateDepartement(Department department) {
        return departmentRepository.findByDepartmentNumber(department.getDepartmentNumber())
                .map(existingDepartement -> {
                    if (!existingDepartement.equals(department)) {
                        existingDepartement.setDepartmentName(department.getDepartmentName());
                    }
                    return departmentRepository.save(existingDepartement);
                })
                .orElseGet(() -> departmentRepository.save(department)
                );
    }

    public Optional<Region> safeUpdateRegion(Region region) {
        try {
            return Optional.of(updateRegion(region));
        } catch (Exception e) {
            LOGGER.warn("Could not update region {}", region);
            return Optional.empty();
        }
    }

    public Optional<Department> safeUpdateDepartement(Department department) {
        try {
            return Optional.of(updateDepartement(department));
        } catch (Exception e) {
            LOGGER.warn("Could not update departement {}", department);
            return Optional.empty();
        }
    }

    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Map<String, Integer> getStats() {
        Map<String, Integer> map = new HashMap<>();
        map.put("regionCount", getAllRegions().size());
        map.put("departementCount", getAllDepartments().size());
        return map;
    }
}

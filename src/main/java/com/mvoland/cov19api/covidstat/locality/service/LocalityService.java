package com.mvoland.cov19api.covidstat.locality.service;

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
import java.util.stream.Collectors;

@Service
public class LocalityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalityService.class);

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

    public Optional<Region> findRegionByCode(String regionCode) {
        return regionRepository.findByRegionCode(regionCode);
    }

    public Optional<Department> findDepartmentByCode(String departmentCode) {
        return departmentRepository.findByDepartmentCode(departmentCode);
    }

    @Transactional
    public Region updateRegion(Region region) {
        return regionRepository.findByRegionCode(region.getRegionCode())
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
        return departmentRepository.findByDepartmentCode(department.getDepartmentCode())
                .map(existingDepartement -> {
                    if (!existingDepartement.equals(department)) {
                        existingDepartement.setDepartmentName(department.getDepartmentName());
                        existingDepartement.setRegion(department.getRegion());
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

    public List<Region> searchRegions(List<String> regionCodes, List<String> regionNames) {
        return getAllRegions().stream()
                .filter(region -> (regionCodes.isEmpty() && regionNames.isEmpty())
                        || regionCodes.contains(region.getRegionCode())
                        || regionNames.contains(region.getRegionName()))
                .collect(Collectors.toList());
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }


    public List<Department> searchDepartments(List<String> departmentCodes, List<String> departmentNames) {
        return getAllDepartments().stream()
                .filter(department -> (departmentCodes.isEmpty() && departmentNames.isEmpty())
                        || departmentCodes.contains(department.getDepartmentCode())
                        || departmentNames.contains(department.getDepartmentName()))
                .collect(Collectors.toList());
    }

    public Map<String, Integer> getStats() {
        Map<String, Integer> map = new HashMap<>();
        map.put("regionCount", getAllRegions().size());
        map.put("departmentCount", getAllDepartments().size());
        return map;
    }
}

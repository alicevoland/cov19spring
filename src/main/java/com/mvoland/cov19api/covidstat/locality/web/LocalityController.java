package com.mvoland.cov19api.covidstat.locality.web;

import com.mvoland.cov19api.covidstat.locality.data.Department;
import com.mvoland.cov19api.covidstat.locality.data.Region;
import com.mvoland.cov19api.covidstat.locality.service.LocalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/locality")
public class LocalityController {

    private final LocalityService localityService;

    @Autowired
    public LocalityController(
            LocalityService localityService
    ) {
        this.localityService = localityService;
    }

    @GetMapping("regions")
    public List<Region> getRegionByCode() {
        return localityService.getAllRegions();
    }

    @GetMapping("departments")
    public List<Department> getAllDepartments() {
        return localityService.getAllDepartments();
    }

    @GetMapping("region/{code}")
    public Region getRegionByCode(
            @PathVariable(name = "code") String regionCode
    ) {
        return localityService
                .findRegionByCode(regionCode)
                .orElseThrow(() -> new RegionNotFoundException(regionCode));
    }

    @GetMapping("department/{code}")
    public Department getDepartmentByCode(
            @PathVariable(name = "code") String departmentCode
    ) {
        return localityService
                .findDepartmentByCode(departmentCode)
                .orElseThrow(() -> new RegionNotFoundException(departmentCode));
    }

    @GetMapping("department/by")
    public Department getDepartmentByName(
            @RequestParam(name = "name") String departmentName
    ) {
        return localityService
                .findDepartmentByName(departmentName)
                .orElseThrow(() -> new RegionNotFoundException(departmentName));
    }

    @GetMapping("region/by")
    public Region getRegionByName(
            @RequestParam(name = "name") String regionName
    ) {
        return localityService
                .findRegionByName(regionName)
                .orElseThrow(() -> new RegionNotFoundException(regionName));
    }


    @GetMapping("stats")
    public Map<String, Integer> getStats() {
        return localityService.getStats();
    }

}

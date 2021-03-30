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

    @GetMapping("regions/all")
    public List<Region> getAllRegions() {
        return localityService.getAllRegions();
    }

    @GetMapping("region/{code}")
    public Region getRegionByCode(
            @PathVariable(name = "code") String regionCode
    ) {
        return localityService
                .findRegionByCode(regionCode)
                .orElseThrow(() -> new RegionNotFoundException(regionCode));
    }

    @GetMapping("regions/search")
    public List<Region> searchRegions(
            @RequestParam(name = "codes", defaultValue = "") List<String> regionsCodes,
            @RequestParam(name = "names", defaultValue = "") List<String> regionsNames
    ) {
        return localityService.searchRegions(regionsCodes, regionsNames);
    }

    @GetMapping("departments/all")
    public List<Department> getAllDepartments() {
        return localityService.getAllDepartments();
    }


    @GetMapping("department/{code}")
    public Department getDepartmentByCode(
            @PathVariable(name = "code") String departmentCode
    ) {
        return localityService
                .findDepartmentByCode(departmentCode)
                .orElseThrow(() -> new RegionNotFoundException(departmentCode));
    }

    @GetMapping("departments/search")
    public List<Department> searchDepartments(
            @RequestParam(name = "codes", defaultValue = "") List<String> departmentCodes,
            @RequestParam(name = "names", defaultValue = "") List<String> departmentNames
    ) {
        return localityService.searchDepartments(departmentCodes, departmentNames);
    }


    @GetMapping("stats")
    public Map<String, Integer> getStats() {
        return localityService.getStats();
    }

}

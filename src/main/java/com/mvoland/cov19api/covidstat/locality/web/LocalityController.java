package com.mvoland.cov19api.covidstat.locality.web;

import com.mvoland.cov19api.covidstat.locality.data.Department;
import com.mvoland.cov19api.covidstat.locality.data.Region;
import com.mvoland.cov19api.covidstat.locality.service.LocalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Region> getAllRegions() {
        return localityService.getAllRegions();
    }

    @GetMapping("region")
    public Region getAllRegions(
            @RequestParam String regionCode
    ) {
        return localityService
                .findRegionByCode(regionCode)
                .orElseThrow(() -> new RegionNotFoundException(regionCode));
    }

    @GetMapping("departments")
    public List<Department> getAllDepartments() {
        return localityService.getAllDepartments();
    }

    @GetMapping("stats")
    public Map<String, Integer> getStats() {
        return localityService.getStats();
    }

}

package com.mvoland.cov19api.web;

import com.mvoland.cov19api.business.service.RegionService;
import com.mvoland.cov19api.business.service.RegionalService;
import com.mvoland.cov19api.data.entity.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/hospdata")
public class HospDataController {

    private final RegionService regionService;
    private final RegionalService regionalService;

    @Autowired
    public HospDataController(RegionService regionService,
                              RegionalService regionalService) {
        this.regionalService = regionalService;
        this.regionService = regionService;

    }

    @GetMapping("regions")
    public List<Region> getAllRegions() {
        return regionService.getAllRegions();
    }


}

package com.mvoland.cov19api.web;

import com.mvoland.cov19api.business.domain.RegionIntensiveCare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/regions")
public class RegionIntensiveCareRestController {
//
//    private final RegionIntensiveCareService regionIntensiveCareService;
//
//    @Autowired
//    public RegionIntensiveCareRestController(RegionIntensiveCareService regionIntensiveCareService) {
//        this.regionIntensiveCareService = regionIntensiveCareService;
//    }
//
//    @GetMapping("all")
//    public List<RegionIntensiveCare> getAllIntensiveCareRegions() {
//        return this.regionIntensiveCareService.getAllRegionIntensiveCares();
//    }
//
//    @GetMapping
//    public List<RegionIntensiveCare> getAllIntensiveCare(
//            @RequestParam(name = "date", required = false) String dateString,
//            @RequestParam(name = "region", required = false) String regionName) {
//        return this.regionIntensiveCareService.getRegionIntensiveCaresFor(dateString, regionName);
//    }


}

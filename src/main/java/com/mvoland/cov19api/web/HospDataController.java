package com.mvoland.cov19api.web;

import com.mvoland.cov19api.business.service.RegionalHospDataService;
import com.mvoland.cov19api.data.entity.Region;
import com.mvoland.cov19api.data.entity.RegionalIntensiveCareAdmission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/hospdata")
public class HospDataController {

    private final RegionalHospDataService regionalHospDataService;

    @Autowired
    public HospDataController(RegionalHospDataService regionalHospDataService) {
        this.regionalHospDataService = regionalHospDataService;

    }

    @GetMapping("regions")
    public List<Region> getAllRegions() {
        return regionalHospDataService.getAllRegions();
    }

//    @GetMapping("icAdmissions")
//    public List<RegionalIntensiveCareAdmission> getIcAdmissionsByRegionNumber()

}

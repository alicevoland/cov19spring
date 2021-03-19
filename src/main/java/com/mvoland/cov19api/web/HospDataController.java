package com.mvoland.cov19api.web;

import com.mvoland.cov19api.hospdata.service.RegionalHospDataService;
import com.mvoland.cov19api.data.hospdata.entity.Region;
import com.mvoland.cov19api.datagouvfr.updateservice.HospDataDatabaseUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/hospdata")
public class HospDataController {

    private final HospDataDatabaseUpdateService updateService;
    private final RegionalHospDataService regionalHospDataService;

    @Autowired
    public HospDataController(
            HospDataDatabaseUpdateService updateService,
            RegionalHospDataService regionalHospDataService) {
        this.updateService = updateService;
        this.regionalHospDataService = regionalHospDataService;

    }

    @GetMapping("regions")
    public List<Region> getAllRegions() {
        return regionalHospDataService.getAllRegions();
    }

    @GetMapping("region")
    public Region getRegionByNumber(@RequestParam(value = "regionNumber") Integer regionNumber) {
        return regionalHospDataService
                .findRegionByNumber(regionNumber)
                .orElseThrow(() -> new RegionNotFoundException(regionNumber));
    }

    @GetMapping("requestUpdate")
    public void requestUpdate() {
        updateService.update(false);
    }

//    @GetMapping("icAdmissions")
//    public List<RegionalIntensiveCareAdmission> getIcAdmissionsByRegionNumber()

}

package com.mvoland.cov19api.tomove.web.hospdata;

import com.mvoland.cov19api.tomove.data.hospdata.entity.RegionalIntensiveCareAdmission;
import com.mvoland.cov19api.tomove.hospdata.RegionalHospDataService;
import com.mvoland.cov19api.covidstat.locality.data.Region;
import com.mvoland.cov19api.tomove.datagouvfr.DataGouvFrHospDataUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/hospdata")
public class HospDataController {

    private final DataGouvFrHospDataUpdateService updateService;
    private final RegionalHospDataService regionalHospDataService;

    @Autowired
    public HospDataController(
            DataGouvFrHospDataUpdateService updateService,
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

    @GetMapping("intensiveCareAdmissions")
    public List<RegionalIntensiveCareAdmission> intensiveCareAdmissions(
            @RequestParam(value = "regionNumber") Integer regionNumber,
            @RequestParam(value = "days", defaultValue = "10", required = false) Integer days) {
        return regionalHospDataService
                .getByRegionNumberAndDays(regionNumber, days);
    }

    @GetMapping("requestUpdate")
    public void requestUpdate() {
        updateService.update(false);
    }

//    @GetMapping("icAdmissions")
//    public List<RegionalIntensiveCareAdmission> getIcAdmissionsByRegionNumber()

}

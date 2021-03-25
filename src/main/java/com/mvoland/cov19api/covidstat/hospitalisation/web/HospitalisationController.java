package com.mvoland.cov19api.covidstat.hospitalisation.web;

import com.mvoland.cov19api.covidstat.hospitalisation.data.entity.RegionalIntensiveCareAdmission;
import com.mvoland.cov19api.covidstat.hospitalisation.service.HospitalisationService;
import com.mvoland.cov19api.covidstat.locality.data.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/hospitalisation" +
        "")
public class HospitalisationController {

    private final HospitalisationService hospitalisationService;

    @Autowired
    public HospitalisationController(HospitalisationService hospitalisationService) {
        this.hospitalisationService = hospitalisationService;
    }


    @GetMapping("intensiveCareAdmissions")
    public List<RegionalIntensiveCareAdmission> intensiveCareAdmissions(
            @RequestParam(value = "regionCode") String regionCode,
            @RequestParam(value = "days", defaultValue = "10", required = false) Integer days) {
        return hospitalisationService
                .getByRegionCodeAndDays(regionCode, days);
    }


    @GetMapping("stats")
    public Map<String, Integer> getStats() {
        return hospitalisationService.getStats();
    }

}

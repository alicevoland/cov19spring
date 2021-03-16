package com.mvoland.cov19api.web;

import com.mvoland.cov19api.data.entity.Region;
import com.mvoland.cov19api.data.entity.RegionalHospitalisation;
import com.mvoland.cov19api.data.entity.RegionalIntensiveCareAdmission;
import com.mvoland.cov19api.datagouvfr.service.HospitalisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/hospitalisations")
public class HospitalisationRestController {

//    private final HospitalisationService hospitalisationService;
//
//    @Autowired
//    public HospitalisationRestController(HospitalisationService hospitalisationService) {
//        this.hospitalisationService = hospitalisationService;
//    }
//
//
//    @GetMapping("regionalIntensiveCareAdmissions")
//    public List<RegionalIntensiveCareAdmission> getAllRegionalIntensiveCareAdmissions() {
//        return this.hospitalisationService.getAllRegionalIntensiveCareAdmissions();
//    }
//
//    @GetMapping("regionalHospitalisations")
//    public List<RegionalHospitalisation> getAllRegionalHospitalisations() {
//        return this.hospitalisationService.getAllRegionalHospitalisations();
//    }
//
//    @GetMapping("regions")
//    public List<Region> getAllRegions() {
//        return this.hospitalisationService.getAllRegions();
//    }
//

}

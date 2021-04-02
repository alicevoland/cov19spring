package com.mvoland.cov19api.covidstat.hospitalisation.web;

import com.mvoland.cov19api.covidstat.hospitalisation.data.entity.RegionalIntensiveCareAdmission;
import com.mvoland.cov19api.covidstat.hospitalisation.service.HospitalisationService;
import com.mvoland.cov19api.covidstat.hospitalisation.web.assembler.RegionalIntensiveCareAdmissionAssembler;
import com.mvoland.cov19api.covidstat.hospitalisation.web.exception.HospitalisationNotFoundException;
import com.mvoland.cov19api.covidstat.locality.service.LocalityService;
import com.mvoland.cov19api.common.ParsingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/hospitalisation")
public class HospitalisationController {

    private final LocalityService localityService;
    private final HospitalisationService hospitalisationService;
    private final RegionalIntensiveCareAdmissionAssembler regionalIntensiveCareAdmissionAssembler;

    @Autowired
    public HospitalisationController(
            LocalityService localityService,
            HospitalisationService hospitalisationService,
            RegionalIntensiveCareAdmissionAssembler regionalIntensiveCareAdmissionAssembler
    ) {
        this.localityService = localityService;
        this.hospitalisationService = hospitalisationService;
        this.regionalIntensiveCareAdmissionAssembler = regionalIntensiveCareAdmissionAssembler;
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

    @GetMapping("/regionalIntensiveCareAdmissions/id/{id}")
    public EntityModel<RegionalIntensiveCareAdmission> oneRegionalIntensiveCareAdmissionById(
            @PathVariable("id") Long id
    ) {
        RegionalIntensiveCareAdmission regionalIntensiveCareAdmission = hospitalisationService.findRegionalIntensiveCareAdmissionById(id)
                .orElseThrow(() -> new HospitalisationNotFoundException("Could not find for this ID"));
        return regionalIntensiveCareAdmissionAssembler.toModel(regionalIntensiveCareAdmission);
    }

    @GetMapping("/regionalIntensiveCareAdmissions")
    public CollectionModel<EntityModel<RegionalIntensiveCareAdmission>> allRegionalIntensiveCareAdmissions() {
        List<RegionalIntensiveCareAdmission> regionalIntensiveCareAdmissions = hospitalisationService.findAllRegionalIntensiveCareAdmission();
        return regionalIntensiveCareAdmissionAssembler.toCollectionModel(regionalIntensiveCareAdmissions);
    }

    @GetMapping("/regionalIntensiveCareAdmissions/search")
    public CollectionModel<EntityModel<RegionalIntensiveCareAdmission>> searchRegionalIntensiveCareAdmissionByRegionAndDates(
            @RequestParam String regionCode,
            @RequestParam String noticeDateBegin,
            @RequestParam String noticeDateEnd
    ) {
        List<RegionalIntensiveCareAdmission> regionalIntensiveCareAdmissions = hospitalisationService.findRegionalIntensiveCareAdmissionByRegionAndDates(
                localityService.findRegionByCode(regionCode)
                        .orElseThrow(() -> new HospitalisationNotFoundException("Could not fin region")),
                ParsingUtils.parseDateOrThrow(noticeDateBegin,
                        () -> new HospitalisationNotFoundException("Could not parse noticeDateBegin")),
                ParsingUtils.parseDateOrThrow(noticeDateEnd,
                        () -> new HospitalisationNotFoundException("Could not parse noticeDateEnd"))
        );
        return regionalIntensiveCareAdmissionAssembler.toCollectionModel(regionalIntensiveCareAdmissions);
    }

}

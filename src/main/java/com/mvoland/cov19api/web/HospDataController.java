package com.mvoland.cov19api.web;

import com.mvoland.cov19api.data.entity.Region;
import com.mvoland.cov19api.data.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/hospdata")
public class HospDataController {

    private final DepartementRepository depRepo;
    private final DepartementalHospitalisationRepository depHospRepo;
    private final DepartementalNewHospitalisationRepository depNewHospRepo;
    private final RegionRepository regionRepo;
    private final RegionalHospitalisationRepository regHospRepo;
    private final RegionalIntensiveCareAdmissionRepository regNewIcRepo;

    @Autowired
    public HospDataController(DepartementRepository depRepo,
                              DepartementalHospitalisationRepository depHospRepo,
                              DepartementalNewHospitalisationRepository depNewHospRepo,
                              RegionRepository regionRepo,
                              RegionalHospitalisationRepository regHospRepo,
                              RegionalIntensiveCareAdmissionRepository regNewIcRepo) {

        this.depRepo = depRepo;
        this.depHospRepo = depHospRepo;
        this.depNewHospRepo = depNewHospRepo;
        this.regionRepo = regionRepo;
        this.regHospRepo = regHospRepo;
        this.regNewIcRepo = regNewIcRepo;
    }

    @GetMapping("regions")
    public List<Region> getAllRegions() {
        return regionRepo.findAll();
    }

}

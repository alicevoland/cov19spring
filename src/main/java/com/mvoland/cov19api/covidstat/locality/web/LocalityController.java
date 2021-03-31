package com.mvoland.cov19api.covidstat.locality.web;

import com.mvoland.cov19api.covidstat.locality.data.Department;
import com.mvoland.cov19api.covidstat.locality.data.Region;
import com.mvoland.cov19api.covidstat.locality.service.LocalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/locality")
public class LocalityController {

    private final LocalityService localityService;
    private final RegionModelAssembler regionModelAssembler;

    @Autowired
    public LocalityController(
            LocalityService localityService,
            RegionModelAssembler regionModelAssembler
    ) {
        this.localityService = localityService;
        this.regionModelAssembler = regionModelAssembler;
    }

    @GetMapping("/regions/id/{id}")
    public EntityModel<Region> oneRegionById(
            @PathVariable Long id
    ) {
        Region region = localityService.findRegionById(id)
                .orElseThrow(() -> new RegionNotFoundException(String.format("for id %d", id)));
        return regionModelAssembler.toModel(region);
    }

    @GetMapping("regions/code/{code}")
    public EntityModel<Region> oneRegionByCode(
            @PathVariable(name = "code") String regionCode
    ) {
        Region region = localityService.findRegionByCode(regionCode)
                .orElseThrow(() -> new RegionNotFoundException(regionCode));
        return regionModelAssembler.toModel(region);
    }

    @GetMapping("/regions")
    public CollectionModel<EntityModel<Region>> allRegions() {
        List<Region> regions = localityService.findAllRegions();
        return regionModelAssembler.toCollectionModel(regions);
    }


    @GetMapping("regions/search")
    public List<Region> searchRegions(
            @RequestParam(name = "codes", defaultValue = "") List<String> regionsCodes,
            @RequestParam(name = "names", defaultValue = "") List<String> regionsNames
    ) {
        return localityService.searchRegions(regionsCodes, regionsNames);
    }

    @GetMapping("departments/all")
    public List<Department> getAllDepartments() {
        return localityService.getAllDepartments();
    }


    @GetMapping("department/{code}")
    public Department getDepartmentByCode(
            @PathVariable(name = "code") String departmentCode
    ) {
        return localityService
                .findDepartmentByCode(departmentCode)
                .orElseThrow(() -> new RegionNotFoundException(departmentCode));
    }

    @GetMapping("departments/search")
    public List<Department> searchDepartments(
            @RequestParam(name = "codes", defaultValue = "") List<String> departmentCodes,
            @RequestParam(name = "names", defaultValue = "") List<String> departmentNames
    ) {
        return localityService.searchDepartments(departmentCodes, departmentNames);
    }


    @GetMapping("stats")
    public Map<String, Integer> getStats() {
        return localityService.getStats();
    }

}

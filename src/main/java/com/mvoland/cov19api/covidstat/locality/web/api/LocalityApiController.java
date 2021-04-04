package com.mvoland.cov19api.covidstat.locality.web.api;

import com.mvoland.cov19api.covidstat.locality.web.assembler.DepartmentModelAssembler;
import com.mvoland.cov19api.covidstat.locality.web.assembler.RegionModelAssembler;
import com.mvoland.cov19api.covidstat.locality.exception.DepartmentNotFoundException;
import com.mvoland.cov19api.covidstat.locality.exception.RegionNotFoundException;
import com.mvoland.cov19api.covidstat.locality.data.entity.Department;
import com.mvoland.cov19api.covidstat.locality.data.entity.Region;
import com.mvoland.cov19api.covidstat.locality.service.LocalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/locality")
public class LocalityApiController {

    private final LocalityService localityService;
    private final RegionModelAssembler regionModelAssembler;
    private final DepartmentModelAssembler departmentModelAssembler;

    @Autowired
    public LocalityApiController(
            LocalityService localityService,
            RegionModelAssembler regionModelAssembler,
            DepartmentModelAssembler departmentModelAssembler
    ) {
        this.localityService = localityService;
        this.regionModelAssembler = regionModelAssembler;
        this.departmentModelAssembler = departmentModelAssembler;
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
            @PathVariable String code
    ) {
        Region region = localityService.findRegionByCode(code)
                .orElseThrow(() -> new RegionNotFoundException(code));
        return regionModelAssembler.toModel(region);
    }

    @GetMapping("/regions")
    public CollectionModel<EntityModel<Region>> allRegions() {
        List<Region> regions = localityService.findAllRegions();
        return regionModelAssembler.toCollectionModel(regions);
    }


    @GetMapping("/regions/search")
    public CollectionModel<EntityModel<Region>> searchRegions(
            @RequestParam(name = "codes", defaultValue = "") List<String> codes,
            @RequestParam(name = "names", defaultValue = "") List<String> names
    ) {
        List<Region> regions = localityService.searchRegions(codes, names);
        return regionModelAssembler.toCollectionModel(regions);
    }


    @GetMapping("/departments/id/{id}")
    public EntityModel<Department> oneDepartmentById(
            @PathVariable Long id
    ) {
        Department department = localityService.findDepartmentById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(String.format("for id %d", id)));
        return departmentModelAssembler.toModel(department);
    }

    @GetMapping("departments/code/{code}")
    public EntityModel<Department> oneDepartmentByCode(
            @PathVariable String code
    ) {
        Department department = localityService.findDepartmentByCode(code)
                .orElseThrow(() -> new DepartmentNotFoundException(code));
        return departmentModelAssembler.toModel(department);
    }

    @GetMapping("/departments")
    public CollectionModel<EntityModel<Department>> allDepartments() {
        List<Department> departments = localityService.findAllDepartments();
        return departmentModelAssembler.toCollectionModel(departments);
    }


    @GetMapping("/departments/search")
    public CollectionModel<EntityModel<Department>> searchDepartments(
            @RequestParam(name = "codes", defaultValue = "") List<String> codes,
            @RequestParam(name = "names", defaultValue = "") List<String> names
    ) {
        List<Department> departments = localityService.searchDepartments(codes, names);
        return departmentModelAssembler.toCollectionModel(departments);
    }

    @GetMapping("/stats")
    public EntityModel<Map<String, Integer>> getStats() {
        return EntityModel.of(localityService.getStats());
    }

}

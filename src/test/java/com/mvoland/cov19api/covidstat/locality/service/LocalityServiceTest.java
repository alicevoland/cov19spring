package com.mvoland.cov19api.covidstat.locality.service;

import com.mvoland.cov19api.covidstat.locality.data.Department;
import com.mvoland.cov19api.covidstat.locality.data.DepartmentRepository;
import com.mvoland.cov19api.covidstat.locality.data.Region;
import com.mvoland.cov19api.covidstat.locality.data.RegionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class LocalityServiceTest {


    private final RegionRepository regionRepository;
    private final DepartmentRepository departmentRepository;
    private final LocalityService localityService;
    private Region region1;
    private Region region2;
    private Department department1;
    private Department department2;
    private Department department3;
    private String nonExistingCode;
    private String nonExistingName;

    @Autowired
    public LocalityServiceTest(
            RegionRepository regionRepository,
            DepartmentRepository departmentRepository,
            LocalityService localityService
    ) {
        this.regionRepository = regionRepository;
        this.departmentRepository = departmentRepository;
        this.localityService = localityService;

    }

    @BeforeEach
    void populateRepositories() {
        this.region1 = regionRepository.save(new Region("1234", "Test Region 1"));
        this.region2 = regionRepository.save(new Region("1235", "Test Region 2"));
        this.department1 = departmentRepository.save(new Department("11", "Test Department 1", region1));
        this.department2 = departmentRepository.save(new Department("12", "Test Department 2", region1));
        this.department3 = departmentRepository.save(new Department("13", "Test Department 3", region2));
        this.nonExistingCode = "34404";
        this.nonExistingName = "3RR0R";
    }

    @Test
    void findRegionByCode() {
        Optional<Region> region = localityService.findRegionByCode(region1.getRegionCode());
        then(region.isPresent()).isTrue();
        //noinspection OptionalGetWithoutIsPresent
        then(region.get()).isEqualTo(region1);
    }

    @Test
    void findDepartmentByCode() {
        Optional<Department> department = localityService.findDepartmentByCode(department1.getDepartmentCode());
        then(department.isPresent()).isTrue();
        //noinspection OptionalGetWithoutIsPresent
        then(department.get()).isEqualTo(department1);
    }


    @Test
    void getAllRegions() {
        List<Region> regions = localityService.getAllRegions();
        then(regions.size()).isEqualTo(2);
    }

    @Test
    void searchRegions_all() {
        List<Region> regions = localityService.searchRegions(List.of(), List.of());
        then(regions.size()).isEqualTo(2);
    }

    @Test
    void searchRegions_emptySearch() {
        List<Region> regions = localityService.searchRegions(List.of(nonExistingCode), List.of(nonExistingName));
        then(regions).isEmpty();
    }

    @Test
    void searchRegions_codes() {
        List<Region> regions = localityService.searchRegions(List.of(nonExistingCode, region2.getRegionCode()), List.of());
        then(regions.size()).isEqualTo(1);
        then(regions.get(0)).isEqualTo(region2);
    }

    @Test
    void searchRegions_names() {
        List<Region> regions = localityService.searchRegions(List.of(), List.of(nonExistingName, region2.getRegionName()));
        then(regions.size()).isEqualTo(1);
        then(regions.get(0)).isEqualTo(region2);
    }

    @Test
    void searchRegions_codesAndNames() {
        List<Region> regions = localityService.searchRegions(List.of(nonExistingCode, region1.getRegionCode()), List.of(nonExistingName, region2.getRegionName()));
        then(regions.size()).isEqualTo(2);
    }

    @Test
    void getAllDepartments() {
        List<Department> departments = localityService.getAllDepartments();
        then(departments.size()).isEqualTo(3);
    }

    @Test
    void searchDepartments_all() {
        List<Department> departments = localityService.searchDepartments(List.of(), List.of());
        then(departments.size()).isEqualTo(3);
    }

    @Test
    void searchDepartments_emptySearch() {
        List<Department> departments = localityService.searchDepartments(List.of(nonExistingCode), List.of(nonExistingName));
        then(departments).isEmpty();
    }

    @Test
    void searchDepartments_codes() {
        List<Department> departments = localityService.searchDepartments(List.of(nonExistingCode, department2.getDepartmentCode()), List.of());
        then(departments.size()).isEqualTo(1);
        then(departments.get(0)).isEqualTo(department2);
    }

    @Test
    void searchDepartments_names() {
        List<Department> departments = localityService.searchDepartments(List.of(), List.of(nonExistingName, department2.getDepartmentName()));
        then(departments.size()).isEqualTo(1);
        then(departments.get(0)).isEqualTo(department2);
    }

    @Test
    void searchDepartments_codesAndNames() {
        List<Department> departments = localityService.searchDepartments(List.of(nonExistingCode, department1.getDepartmentCode()), List.of(nonExistingName, department2.getDepartmentName()));
        then(departments.size()).isEqualTo(2);
    }

    @Test
    void getStats() {
        Map<String, Integer> stats = localityService.getStats();
        then(stats.get("regionCount")).isEqualTo(2);
        then(stats.get("departmentCount")).isEqualTo(3);
    }
}
package com.mvoland.cov19api.covidstat.locality.service;

import com.mvoland.cov19api.covidstat.locality.data.Department;
import com.mvoland.cov19api.covidstat.locality.data.DepartmentRepository;
import com.mvoland.cov19api.covidstat.locality.data.Region;
import com.mvoland.cov19api.covidstat.locality.data.RegionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ExtendWith(MockitoExtension.class)
@Transactional
public class LocalityServiceTest {

    @MockBean RegionRepository regionRepository;

    @MockBean DepartmentRepository departmentRepository;

    @Autowired LocalityService localityService;

    Region region1 = new Region(1L, "1234", "Test Region 1");
    Region region2 = new Region(2L, "1235", "Test Region 2");
    Department department1 = new Department(1L, "11", "Test Department 1", region1);
    Department department2 = new Department(2L, "12", "Test Department 2", region1);
    Department department3 = new Department(3L, "13", "Test Department 3", region2);
    String nonExistingCode = "34404";
    String nonExistingName = "3RR0R";
    List<Region> testRegions = List.of(region1, region2);
    List<Department> testDepartments = List.of(department1, department2, department3);

    @BeforeEach
    void mockRepositories() {
        testRegions.forEach(region ->
                Mockito.when(regionRepository.findByRegionCode(region.getRegionCode()))
                        .thenReturn(Optional.of(region)));
        Mockito.when(regionRepository.findByRegionCode(nonExistingCode))
                .thenReturn(Optional.empty());
        Mockito.when(regionRepository.findAll())
                .thenReturn(testRegions);

        testDepartments.forEach(department ->
                Mockito.when(departmentRepository.findByDepartmentCode(department.getDepartmentCode()))
                        .thenReturn(Optional.of(department)));
        Mockito.when(departmentRepository.findByDepartmentCode(nonExistingCode))
                .thenReturn(Optional.empty());
        Mockito.when(departmentRepository.findAll())
                .thenReturn(testDepartments);
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
        then(regions).isEqualTo(testRegions);
    }

    @Test
    void searchRegions_all() {
        List<Region> regions = localityService.searchRegions(List.of(), List.of());
        then(regions).isEqualTo(testRegions);
    }

    @Test
    void searchRegions_emptySearch() {
        List<Region> regions = localityService.searchRegions(List.of(nonExistingCode), List.of(nonExistingName));
        then(regions).isEqualTo(List.of());
    }

    @Test
    void searchRegions_codes() {
        List<Region> regions = localityService.searchRegions(List.of(nonExistingCode, region2.getRegionCode()), List.of());
        then(regions).isEqualTo(List.of(region2));
    }

    @Test
    void searchRegions_names() {
        List<Region> regions = localityService.searchRegions(List.of(), List.of(nonExistingName, region2.getRegionName()));
        then(regions).isEqualTo(List.of(region2));
    }

    @Test
    void searchRegions_codesAndNames() {
        List<Region> regions = localityService.searchRegions(List.of(nonExistingCode, region1.getRegionCode()), List.of(nonExistingName, region2.getRegionName()));
        then(regions).isEqualTo(List.of(region1, region2));
    }

    @Test
    void getAllDepartments() {
        List<Department> departments = localityService.getAllDepartments();
        then(departments).isEqualTo(testDepartments);
    }

    @Test
    void searchDepartments_all() {
        List<Department> departments = localityService.searchDepartments(List.of(), List.of());
        then(departments).isEqualTo(testDepartments);
    }

    @Test
    void searchDepartments_emptySearch() {
        List<Department> departments = localityService.searchDepartments(List.of(nonExistingCode), List.of(nonExistingName));
        then(departments).isEqualTo(List.of());
    }

    @Test
    void searchDepartments_codes() {
        List<Department> departments = localityService.searchDepartments(List.of(nonExistingCode, department2.getDepartmentCode()), List.of());
        then(departments).isEqualTo(List.of(department2));
    }

    @Test
    void searchDepartments_names() {
        List<Department> departments = localityService.searchDepartments(List.of(), List.of(nonExistingName, department2.getDepartmentName()));
        then(departments).isEqualTo(List.of(department2));
    }

    @Test
    void searchDepartments_codesAndNames() {
        List<Department> departments = localityService.searchDepartments(List.of(nonExistingCode, department1.getDepartmentCode()), List.of(nonExistingName, department2.getDepartmentName()));
        then(departments).isEqualTo(List.of(department1, department2));
    }

    @Test
    void getStats() {
        Map<String, Integer> stats = localityService.getStats();
        then(stats.get("regionCount")).isEqualTo(2);
        then(stats.get("departmentCount")).isEqualTo(3);
    }
}
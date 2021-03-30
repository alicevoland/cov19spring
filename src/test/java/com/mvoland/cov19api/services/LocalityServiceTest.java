package com.mvoland.cov19api.services;

import com.mvoland.cov19api.covidstat.locality.data.Department;
import com.mvoland.cov19api.covidstat.locality.data.DepartmentRepository;
import com.mvoland.cov19api.covidstat.locality.data.Region;
import com.mvoland.cov19api.covidstat.locality.data.RegionRepository;
import com.mvoland.cov19api.covidstat.locality.service.LocalityService;
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

    @Autowired private RegionRepository regionRepository;

    @Autowired private DepartmentRepository departmentRepository;

    @Autowired private LocalityService localityService;

    private Region region1;
    private Department department1;
    private Department department2;

    private void givenSample() {
        region1 = regionRepository.save(new Region("1234", "Test Region"));
        department1 = departmentRepository.save(new Department("12", "Test Department 1", region1));
        department2 = departmentRepository.save(new Department("13", "Test Department 2", region1));
    }

    @Test
    void testGetStats() {
        //Given
        givenSample();
        //When
        Map<String, Integer> stats = localityService.getStats();
        //Then
        then(stats.size()).isEqualTo(2);
        then(stats.get("regionCount")).isEqualTo(1);
        then(stats.get("departmentCount")).isEqualTo(2);
    }

    @Test
    void testGetAllRegions() {
        //Given
        givenSample();
        //When
        List<Region> regions = localityService.getAllRegions();
        //Then
        then(regions.size()).isEqualTo(1);
    }

    @Test
    void testGetAllDepartments() {
        //Given
        givenSample();
        //When
        List<Department> departments = localityService.getAllDepartments();
        //Then
        then(departments.size()).isEqualTo(2);
    }

    @Test
    void testFindDepartmentByCode() {
        //Given
        givenSample();
        //When
        Optional<Department> department = localityService.findDepartmentByCode(department1.getDepartmentCode());
        //Then
        then(department.isPresent()).isTrue();
        then(department.get().getDepartmentName()).isEqualTo(department1.getDepartmentName());
    }

}
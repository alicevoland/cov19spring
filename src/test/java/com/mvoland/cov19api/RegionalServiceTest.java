package com.mvoland.cov19api;

import com.mvoland.cov19api.covidstat.locality.data.Region;
import com.mvoland.cov19api.covidstat.locality.data.RegionRepository;
import com.mvoland.cov19api.covidstat.hospitalisation.service.HospitalisationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class RegionalServiceTest {

//    @Autowired
//    private RegionRepository regionRepository;
//
//    @Autowired
//    private HospitalisationService hospitalisationService;
//
//    @DisplayName("Return region by regionNumber from service layer")
//    @Test
//    void getRegions() {
//
//        //Given
//        Region savedRegion = regionRepository.save(new Region(1234, "Test Region"));
//
//        //When
//        Optional<Region> optionalRegion = hospitalisationService.findRegionByNumber(savedRegion.getRegionNumber());
//
//        //Then
//        then(optionalRegion.isPresent());
//        then(optionalRegion.get().getRegionName()).isEqualTo("Test Region");
//
//    }
}

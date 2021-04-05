package com.mvoland.cov19api.covidstat.hospitalisation.web.api;

import com.mvoland.cov19api.AbstractControllerTest;
import com.mvoland.cov19api.covidstat.hospitalisation.data.entity.RegionalIntensiveCareAdmission;
import com.mvoland.cov19api.covidstat.hospitalisation.service.HospitalisationService;
import com.mvoland.cov19api.covidstat.hospitalisation.web.assembler.RegionalIntensiveCareAdmissionAssembler;
import com.mvoland.cov19api.covidstat.locality.data.entity.Region;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.FieldDescriptor;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = HospitalisationApiController.class)
@ExtendWith(value = {RestDocumentationExtension.class})
@Import({RegionalIntensiveCareAdmissionAssembler.class})
class HospitalisationApiControllerTest extends AbstractControllerTest {

    @MockBean
    private HospitalisationService hospitalisationService;

    List<FieldDescriptor> regionalIntensiveCareAdmissionFields = List.of(
            fieldWithPath("id").description("Admission ID"),
            fieldWithPath("noticeDate").description("Date of admission"),
            fieldWithPath("intensiveCareAdmissionCount").description("Admission count")
    );
    List<FieldDescriptor> regionFields = List.of(
            fieldWithPath("id").description("Region ID"),
            fieldWithPath("regionCode").description("Region code"),
            fieldWithPath("regionName").description("Region name")
    );
    List<FieldDescriptor> selfFields = List.of(
            fieldWithPath("_links.self.href").ignored(),
            fieldWithPath("_links.all.href").ignored()
    );

    @Test
    void searchRegionalIntensiveCareAdmissionByRegionAndDates() throws Exception {
        Region region = new Region(1L, "code", "region name");
        LocalDate noticeBegin = LocalDate.now();
        LocalDate noticeEnd = noticeBegin.minusDays(2);
        List<RegionalIntensiveCareAdmission> admissions = List.of(
                new RegionalIntensiveCareAdmission(100L, region, noticeBegin, 4),
                new RegionalIntensiveCareAdmission(101L, region, noticeBegin.plusDays(1), 2),
                new RegionalIntensiveCareAdmission(102L, region, noticeBegin.plusDays(2), 3)
        );
        Mockito.when(hospitalisationService.findRegionalIntensiveCareAdmissionByRegionAndDates(
                region.getRegionCode(), noticeBegin.toString(), noticeEnd.toString()))
                .thenReturn(admissions);


        mockMvc.perform(get("/api/hospitalisation/regionalIntensiveCareAdmissions/search")
                .param("regionCode", region.getRegionCode())
                .param("noticeDateBegin", noticeBegin.toString())
                .param("noticeDateEnd", noticeEnd.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

//                .andExpect(jsonPath("regionCode").value(region.getRegionCode()))

                .andDo(document("hospitalisation/regionalIntensiveCareAdmissions-search",
                        requestParameters(
                                parameterWithName("regionCode").description("Region code"),
                                parameterWithName("noticeDateBegin").description("From date"),
                                parameterWithName("noticeDateEnd").description("To date")),
                        responseFields()
                                .andWithPrefix(
                                        "_embedded.regionalIntensiveCareAdmissions[].",
                                        regionalIntensiveCareAdmissionFields)
                                .andWithPrefix(
                                        "_embedded.regionalIntensiveCareAdmissions[].",
                                        selfFields)
                                .andWithPrefix(
                                        "_embedded.regionalIntensiveCareAdmissions[].region.",
                                        regionFields)));

    }
}
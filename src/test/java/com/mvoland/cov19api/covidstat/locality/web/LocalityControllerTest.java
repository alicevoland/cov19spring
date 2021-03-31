package com.mvoland.cov19api.covidstat.locality.web;

import com.mvoland.cov19api.covidstat.locality.data.Department;
import com.mvoland.cov19api.covidstat.locality.data.Region;
import com.mvoland.cov19api.covidstat.locality.service.LocalityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = LocalityController.class)
@ExtendWith({RestDocumentationExtension.class})
public class LocalityControllerTest {

    private final Region region1 = new Region(1L, "1234", "Test Region 1");
    private final Region region2 = new Region(2L, "1235", "Test Region 2");
    private final Department department1 = new Department(1L, "11", "Test Department 1", region1);
    private final Department department2 = new Department(2L, "12", "Test Department 2", region1);
    private final Department department3 = new Department(3L, "13", "Test Department 3", region2);
    private final String nonExistingCode = "34404";
    private final String nonExistingName = "3RR0R";
    private MockMvc mockMvc;
    @MockBean
    private LocalityService localityService;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation).uris()
                        .withScheme("https")
                        .withHost("cov19api.herokuapp.com")
                        .withPort(80))
                .build();
    }

    @Test
    void getAllRegions() throws Exception {
        Mockito.when(localityService.getAllRegions())
                .thenReturn(List.of(region1, region2));

        mockMvc.perform(get("/api/v1/locality/regions/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].regionCode").value(region1.getRegionCode()))
                .andExpect(jsonPath("$[0].regionName").value(region1.getRegionName()))
                .andDo(document("locality/regions-all"));
    }

    @Test
    void getRegionByCode_RegionExists() throws Exception {
        Mockito.when(localityService.findRegionByCode(this.region1.getRegionCode()))
                .thenReturn(Optional.of(region1));

        mockMvc.perform(get("/api/v1/locality/region/" + region1.getRegionCode()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("regionCode").value(region1.getRegionCode()))
                .andDo(document("locality/region-code-exists"));
    }

    @Test
    void getRegionByCode_RegionDoesNotExist() throws Exception {
        Mockito.when(localityService.findRegionByCode(nonExistingCode))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/locality/region/" + nonExistingCode))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("Could not find")))
                .andDo(document("locality/region-code-does-not-exist"));
    }


    @Test
    void searchRegions() throws Exception {
        String[] regionNames = new String[]{region1.getRegionName()};
        String[] regionCodes = new String[]{region2.getRegionCode()};
        Mockito.when(localityService.searchRegions(List.of(regionCodes), List.of(regionNames)))
                .thenReturn(List.of(region1, region2));

        mockMvc.perform(
                get("/api/v1/locality/regions/search")
                        .param("codes", regionCodes)
                        .param("names", regionNames)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].regionCode").value(region1.getRegionCode()))
                .andExpect(jsonPath("$[0].regionName").value(region1.getRegionName()))
                .andDo(document("locality/regions-search"));
    }

    @Test
    void getAllDepartments() throws Exception {
        Mockito.when(localityService.getAllDepartments())
                .thenReturn(List.of(department1, department2, department3));

        mockMvc.perform(get("/api/v1/locality/departments/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].departmentCode").value(department1.getDepartmentCode()))
                .andExpect(jsonPath("$[0].departmentName").value(department1.getDepartmentName()))
                .andDo(document("locality/departments-all"));

    }


    @Test
    void getDepartmentByCode_DepartmentExists() throws Exception {
        Mockito.when(localityService.findDepartmentByCode(this.department1.getDepartmentCode()))
                .thenReturn(Optional.of(department1));

        mockMvc.perform(get("/api/v1/locality/department/" + department1.getDepartmentCode()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("departmentCode").value(department1.getDepartmentCode()))
                .andDo(document("locality/department-code-exists"));
    }

    @Test
    void getDepartmentByCode_DepartmentDoesNotExist() throws Exception {
        Mockito.when(localityService.findDepartmentByCode(nonExistingCode))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/locality/department/" + nonExistingCode))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("Could not find")))
                .andDo(document("locality/department-code-does-not-exist"));
    }

    @Test
    void searchDepartments() throws Exception {
        String[] departmentNames = new String[]{department1.getDepartmentName()};
        String[] departmentCodes = new String[]{department2.getDepartmentCode()};
        Mockito.when(localityService.searchDepartments(List.of(departmentCodes), List.of(departmentNames)))
                .thenReturn(List.of(department1, department2));

        mockMvc.perform(
                get("/api/v1/locality/departments/search")
                        .param("codes", departmentCodes)
                        .param("names", departmentNames)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].departmentCode").value(department1.getDepartmentCode()))
                .andExpect(jsonPath("$[0].departmentName").value(department1.getDepartmentName()))
                .andDo(document("locality/departments-search"));
    }

    @Test
    void getStats() throws Exception {
        Map<String, Integer> map = new HashMap<>();
        map.put("regionCount", 3);
        map.put("departementCount", 10);

        Mockito.when(localityService.getStats())
                .thenReturn(map);

        mockMvc.perform(get("/api/v1/locality/stats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("regionCount").value(3))
                .andExpect(jsonPath("departementCount").value(10))
                .andDo(document("locality/stats"));
    }
}
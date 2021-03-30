package com.mvoland.cov19api.api;

import com.mvoland.cov19api.covidstat.locality.data.Department;
import com.mvoland.cov19api.covidstat.locality.data.Region;
import com.mvoland.cov19api.covidstat.locality.service.LocalityService;
import com.mvoland.cov19api.covidstat.locality.web.LocalityController;
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
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = LocalityController.class)
@ExtendWith({RestDocumentationExtension.class})
public class LocalityControllerTest {

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation)).build();
    }

    private MockMvc mockMvc;

    @MockBean
    private LocalityService localityService;


    @Test
    void testGetRegionByCodeWhenRegionExists() throws Exception {
        Region testRegion = new Region(1L, "123ABC", "Test Region");

        Mockito.when(localityService.findRegionByCode(testRegion.getRegionCode()))
                .thenReturn(Optional.of(testRegion));

        mockMvc.perform(get("/api/v1/locality/region/" + testRegion.getRegionCode()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(testRegion.getRegionName())))
                .andDo(document("locality/region-code-exists"));
    }

    private Region region1 = new Region(1L, "1234", "Test Region");
    private Department department1 = new Department(1L, "12", "Test Department 1", region1);
    private Department department2 = new Department(2L, "13", "Test Department 2", region1);
    private String nonExistingCode = "34404";
    private String nonExistingName = "3RR0R";


    @Test
    void testGetRegionByCodeWhenRegionDoesNotExists() throws Exception {

        Mockito.when(localityService.findRegionByCode(nonExistingCode))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/locality/region/" + nonExistingCode))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("Could not find")))
                .andDo(document("locality/region-code-does-not-exist"));
    }

    @Test
    void testGetDepartmentByCodeWhenDepartmentExists() throws Exception {

        Mockito.when(localityService.findDepartmentByCode(department1.getDepartmentCode()))
                .thenReturn(Optional.of(department1));

        mockMvc.perform(get("/api/v1/locality/department/" + department1.getDepartmentCode()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(department1.getDepartmentCode())))
                .andDo(document("locality/department-code-exists"));
    }

    @Test
    void testGetDepartmentByCodeWhenDepartmentDoesNotExist() throws Exception {

        Mockito.when(localityService.findDepartmentByCode(nonExistingCode))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/locality/department/" + nonExistingCode))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("Could not find")))
                .andDo(document("locality/department-code-does-not-exist"));
    }

    @Test
    void testGetDepartmentByNameWhenDepartmentDoesNotExist() throws Exception {

        Mockito.when(localityService.findDepartmentByName(nonExistingName))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/locality/department/by?name=" + nonExistingName))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("Could not find")))
                .andDo(document("locality/department-name-does-not-exist"));
    }

    @Test
    void testGetDepartmentByNameWhenDepartmentExists() throws Exception {

        Mockito.when(localityService.findDepartmentByName(department2.getDepartmentName()))
                .thenReturn(Optional.of(department2));

        mockMvc.perform(get("/api/v1/locality/department/by?name=" + department2.getDepartmentName()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(department2.getId()))
                .andExpect(jsonPath("departmentCode").value(department2.getDepartmentCode()))
                .andExpect(jsonPath("departmentName").value(department2.getDepartmentName()))
                .andDo(document("locality/department-name-exists"));
    }

    @Test
    void testGetStats() throws Exception {
        Map<String, Integer> map = new HashMap<>();
        map.put("regionCount", 3);
        map.put("departementCount", 10);

        Mockito.when(localityService.getStats())
                .thenReturn(map);

        mockMvc.perform(get("/api/v1/locality/stats"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("regionCount")))
                .andDo(document("locality/stats"));
    }
}

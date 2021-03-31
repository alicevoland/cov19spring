package com.mvoland.cov19api.covidstat.locality.web;

import com.mvoland.cov19api.AbstractControllerTest;
import com.mvoland.cov19api.covidstat.locality.data.Department;
import com.mvoland.cov19api.covidstat.locality.data.Region;
import com.mvoland.cov19api.covidstat.locality.service.LocalityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.FieldDescriptor;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = LocalityController.class)
@ExtendWith(value = {RestDocumentationExtension.class})
@Import({RegionModelAssembler.class, DepartmentModelAssembler.class})
public class LocalityControllerTest extends AbstractControllerTest {

    private final Region region1 = new Region(1L, "1234", "Test Region 1");
    private final Region region2 = new Region(2L, "1235", "Test Region 2");
    private final Department department1 = new Department(1L, "11", "Test Department 1", region1);
    private final Department department2 = new Department(2L, "12", "Test Department 2", region1);
    private final Department department3 = new Department(3L, "13", "Test Department 3", region2);
    private final String nonExistingCode = "34404";
    private final String nonExistingName = "3RR0R";

    @MockBean
    private LocalityService localityService;

    List<FieldDescriptor> departmentFields = List.of(
            fieldWithPath("id").description("Department ID"),
            fieldWithPath("departmentCode").description("Department code"),
            fieldWithPath("departmentName").description("Department name")
    );
    List<FieldDescriptor> regionFields = List.of(
            fieldWithPath("id").description("Region ID"),
            fieldWithPath("regionCode").description("Region code"),
            fieldWithPath("regionName").description("Region name")
    );
    List<FieldDescriptor> selfFields = List.of(
            fieldWithPath("_links.self.href").ignored(),
            fieldWithPath("_links.selfByCode.href").ignored(),
            fieldWithPath("_links.all.href").ignored()
    );

    @Test
    void oneRegionById_regionExists() throws Exception {
        Region region = new Region(1L, "1234", "Test Region 1");
        Mockito.when(localityService.findRegionById(region.getId()))
                .thenReturn(Optional.of(region));

        mockMvc.perform(get("/api/v1/locality/regions/id/{id}", region.getId())
                .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect(jsonPath("regionCode").value(region.getRegionCode()))

                .andDo(document("locality/region-id-exists",
                        pathParameters(
                                parameterWithName("id").description("Region ID")),
                        responseFields()
                                .and(selfFields)
                                .and(regionFields)));
    }

    @Test
    void oneRegionByCode_regionExists() throws Exception {
        Region region = new Region(1L, "1234", "Test Region 1");
        Mockito.when(localityService.findRegionByCode(region.getRegionCode()))
                .thenReturn(Optional.of(region));

        mockMvc.perform(get("/api/v1/locality/regions/code/{code}", region.getRegionCode())
                .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect(jsonPath("regionCode").value(region.getRegionCode()))

                .andDo(document("locality/region-code-exists",
                        pathParameters(
                                parameterWithName("code").description("Region code")),
                        responseFields()
                                .and(selfFields)
                                .and(regionFields)));
    }


    @Test
    void allRegions() throws Exception {
        Region region1 = new Region(1L, "1234", "Test Region 1");
        Region region2 = new Region(2L, "1235", "Test Region 2");
        Mockito.when(localityService.findAllRegions())
                .thenReturn(List.of(region1, region2));


        mockMvc.perform(get("/api/v1/locality/regions")
                .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect(jsonPath("_embedded.regions", hasSize(2)))
                .andExpect(jsonPath("_embedded.regions[0].regionCode").value(region1.getRegionCode()))
                .andExpect(jsonPath("_embedded.regions[0].regionName").value(region1.getRegionName()))

                .andDo(document("locality/regions-all",
                        responseFields(
                                fieldWithPath("_embedded.regions").description("Array of regions"))
                                .andWithPrefix("_embedded.regions[].", selfFields)
                                .andWithPrefix("_embedded.regions[].", regionFields)
                ));
    }

    @Test
    void searchRegions_2() throws Exception {
        Region region1 = new Region(1L, "1234", "Test Region 1");
        Region region2 = new Region(2L, "1235", "Test Region 2");
        Mockito.when(localityService.searchRegions(List.of(region1.getRegionCode()), List.of(region2.getRegionName())))
                .thenReturn(List.of(region1, region2));

        mockMvc.perform(
                get("/api/v1/locality/regions/search")
                        .param("codes", region1.getRegionCode())
                        .param("names", new String[]{region2.getRegionName()})
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

                .andExpect(jsonPath("_embedded.regions", hasSize(2)))
                .andExpect(jsonPath("_embedded.regions[0].regionCode").value(region1.getRegionCode()))
                .andExpect(jsonPath("_embedded.regions[0].regionName").value(region1.getRegionName()))

                .andDo(document("locality/regions-search",
                        requestParameters(
                                parameterWithName("codes").description("If present, select only these regions (OR)"),
                                parameterWithName("names").description("If present, select only these regions (OR)")),
                        responseFields(
                                fieldWithPath("_embedded.regions").description("Array of regions"))
                                .andWithPrefix("_embedded.regions[].", selfFields)
                                .andWithPrefix("_embedded.regions[].", regionFields)
                ));
    }


    @Test
    void oneDepartmentById_departmentExists() throws Exception {
        Region region = new Region(1L, "1234", "Test Region 1");
        Department department = new Department(1L, "1234", "Test Department 1", region);
        Mockito.when(localityService.findDepartmentById(department.getId()))
                .thenReturn(Optional.of(department));

        mockMvc.perform(get("/api/v1/locality/departments/id/{id}", department.getId())
                .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect(jsonPath("departmentCode").value(department.getDepartmentCode()))

                .andDo(document("locality/department-id-exists",
                        pathParameters(
                                parameterWithName("id").description("Department ID")),
                        responseFields()
                                .and(selfFields)
                                .and(departmentFields)
                                .andWithPrefix("region.", regionFields)));
    }

    @Test
    void oneDepartmentByCode_departmentExists() throws Exception {
        Region region = new Region(1L, "1234", "Test Region 1");
        Department department = new Department(1L, "1234", "Test Department 1", region);
        Mockito.when(localityService.findDepartmentByCode(department.getDepartmentCode()))
                .thenReturn(Optional.of(department));

        mockMvc.perform(get("/api/v1/locality/departments/code/{code}", department.getDepartmentCode())
                .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect(jsonPath("departmentCode").value(department.getDepartmentCode()))

                .andDo(document("locality/department-code-exists",
                        pathParameters(
                                parameterWithName("code").description("Department code")),
                        responseFields()
                                .and(selfFields)
                                .and(departmentFields)
                                .andWithPrefix("region.", regionFields)));
    }


    @Test
    void allDepartments() throws Exception {
        Region region = new Region(1L, "1234", "Test Region 1");
        Department department1 = new Department(1L, "1234", "Test Department 1", region);
        Department department2 = new Department(2L, "1235", "Test Department 2", region);
        Mockito.when(localityService.findAllDepartments())
                .thenReturn(List.of(department1, department2));


        mockMvc.perform(get("/api/v1/locality/departments")
                .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect(jsonPath("_embedded.departments", hasSize(2)))
                .andExpect(jsonPath("_embedded.departments[0].departmentCode").value(department1.getDepartmentCode()))
                .andExpect(jsonPath("_embedded.departments[0].departmentName").value(department1.getDepartmentName()))

                .andDo(document("locality/departments-all",
                        responseFields(
                                fieldWithPath("_embedded.departments").description("Array of departments"))
                                .andWithPrefix("_embedded.departments[].", selfFields)
                                .andWithPrefix("_embedded.departments[].", departmentFields)
                                .andWithPrefix("_embedded.departments[].region.", regionFields)
                ));
    }

    @Test
    void searchDepartments_2() throws Exception {
        Region region = new Region(1L, "1234", "Test Region 1");
        Department department1 = new Department(1L, "1234", "Test Department 1", region);
        Department department2 = new Department(2L, "1235", "Test Department 2", region);
        Mockito.when(localityService.searchDepartments(List.of(department1.getDepartmentCode()), List.of(department2.getDepartmentName())))
                .thenReturn(List.of(department1, department2));

        mockMvc.perform(
                get("/api/v1/locality/departments/search")
                        .param("codes", department1.getDepartmentCode())
                        .param("names", new String[]{department2.getDepartmentName()})
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

                .andExpect(jsonPath("_embedded.departments", hasSize(2)))
                .andExpect(jsonPath("_embedded.departments[0].departmentCode").value(department1.getDepartmentCode()))
                .andExpect(jsonPath("_embedded.departments[0].departmentName").value(department1.getDepartmentName()))

                .andDo(document("locality/departments-search",
                        requestParameters(
                                parameterWithName("codes").description("If present, select only these departments (OR)"),
                                parameterWithName("names").description("If present, select only these departments (OR)")),
                        responseFields(
                                fieldWithPath("_embedded.departments").description("Array of departments"))
                                .andWithPrefix("_embedded.departments[].", selfFields)
                                .andWithPrefix("_embedded.departments[].", departmentFields)
                                .andWithPrefix("_embedded.departments[].region.", regionFields)
                ));
    }

//
//    @Test
//    void getStats() throws Exception {
//        Map<String, Integer> map = new HashMap<>();
//        map.put("regionCount", 3);
//        map.put("departementCount", 10);
//
//        Mockito.when(localityService.getStats())
//                .thenReturn(map);
//
//        mockMvc.perform(get("/api/v1/locality/stats"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("regionCount").value(3))
//                .andExpect(jsonPath("departementCount").value(10))
//                .andDo(document("locality/stats"));
//    }
}
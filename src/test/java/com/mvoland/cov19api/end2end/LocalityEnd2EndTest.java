package com.mvoland.cov19api.end2end;

import com.mvoland.cov19api.covidstat.locality.data.Department;
import com.mvoland.cov19api.covidstat.locality.data.DepartmentRepository;
import com.mvoland.cov19api.covidstat.locality.data.Region;
import com.mvoland.cov19api.covidstat.locality.data.RegionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ExtendWith({RestDocumentationExtension.class})

@Transactional
public class LocalityEnd2EndTest {

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation).uris().withScheme("https"))
                .build();
    }

    private MockMvc mockMvc;


    @Autowired private RegionRepository regionRepository;

    @Autowired private DepartmentRepository departmentRepository;


    private void givenSample() {
        Region region1 = regionRepository.save(new Region("1234", "Test Region"));
        Department department1 = departmentRepository.save(new Department("12", "Test Department 1", region1));
        Department department2 = departmentRepository.save(new Department("13", "Test Department 2", region1));
    }

    @Test
    void testGetStats() throws Exception {
        givenSample();
        mockMvc.perform(get("/api/v1/locality/stats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("regionCount").value(1))
                .andExpect(jsonPath("departmentCount").value(2))
                .andDo(document("locality/stats2"));

    }
}
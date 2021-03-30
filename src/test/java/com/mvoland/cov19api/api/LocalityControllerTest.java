package com.mvoland.cov19api.api;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        Mockito.when(localityService.findRegionByCode("123ABC"))
                .thenReturn(Optional.of(new Region(1L, "123ABC", "ExistingRegion Name")));

        mockMvc.perform(get("/api/v1/locality/region?code=123ABC"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("ExistingRegion Name")))
                .andDo(document("locality/region-code-exists"));
    }

    @Test
    void testGetRegionByCodeWhenRegionDoesNotExists() throws Exception {
        Mockito.when(localityService.findRegionByCode("123XYZ"))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/locality/region?code=123XYZ"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("Could not find")))
                .andDo(document("locality/region-code-does-not-exist"));
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

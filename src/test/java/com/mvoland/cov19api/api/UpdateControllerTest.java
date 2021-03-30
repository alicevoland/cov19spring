package com.mvoland.cov19api.api;

import com.mvoland.cov19api.datasource.service.UpdateService;
import com.mvoland.cov19api.datasource.web.UpdateController;
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

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = UpdateController.class)
@ExtendWith({RestDocumentationExtension.class})
public class UpdateControllerTest {

    private MockMvc mockMvc;
    @MockBean
    private UpdateService updateService;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation).uris().withScheme("https"))
                .build();
    }

    @Test
    void testFullUpdate() throws Exception {
        Mockito.when(updateService.requestFullUpdate())
                .thenReturn(true);

        mockMvc.perform(get("/api/v1/update/full"))
                .andExpect(status().isOk())
                .andDo(document("update/full"));
    }

    @Test
    void testUpdateSince() throws Exception {
        Mockito.when(updateService.requestUpdateDays(3))
                .thenReturn(true);

        mockMvc.perform(get("/api/v1/update/since?days=3"))
                .andExpect(status().isOk())
                .andDo(document("update/since"));
    }

}

package com.mvoland.cov19api.datasource.web;

import com.mvoland.cov19api.AbstractControllerTest;
import com.mvoland.cov19api.covidstat.locality.web.DepartmentModelAssembler;
import com.mvoland.cov19api.covidstat.locality.web.RegionModelAssembler;
import com.mvoland.cov19api.datasource.service.UpdateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.restdocs.RestDocumentationExtension;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UpdateController.class)
@ExtendWith(value = {RestDocumentationExtension.class})
@Import({RegionModelAssembler.class, DepartmentModelAssembler.class})
class UpdateControllerTest extends AbstractControllerTest {

    @MockBean
    private UpdateService updateService;

    @Test
    void requestUpdateDays() throws Exception {
        Mockito.when(updateService.requestUpdateDays(3))
                .thenReturn(true);

        mockMvc.perform(get("/api/v1/update/since")
                .param("days", "3")
        )
                .andExpect(status().isOk())
                .andDo(document("update/since", requestParameters(
                        parameterWithName("days").description("Fetch data <days> before today")
                )));
    }

    @Test
    void requestFullUpdate() throws Exception {
        Mockito.when(updateService.requestFullUpdate())
                .thenReturn(true);

        mockMvc.perform(get("/api/v1/update/full"))
                .andExpect(status().isOk())
                .andDo(document("update/full"));
    }
}
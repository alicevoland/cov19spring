package com.mvoland.cov19api;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.removeHeaders;

public class AbstractControllerTest {

    protected MockMvc mockMvc;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation).uris()
                        .withScheme("https")
                        .withHost("https://cov19spring.herokuapp.com")
                        .withPort(80))
                .apply(documentationConfiguration(restDocumentation).operationPreprocessors()
                        .withRequestDefaults(removeHeaders("Content-Length", "Content-Type", "Accept"), prettyPrint())
                        .withResponseDefaults(removeHeaders("Content-Length", "Content-Type", "Accept"), prettyPrint()))
                .build();
    }
}

package com.crossover.trial.journals.controller;

import com.crossover.trial.journals.service.JournalService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithUserDetails;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Anghel Leonard
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UploadExceptionTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private JournalService journalService;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }

    @Test
    @WithUserDetails("publisher1")
    public void uploadWithException() throws Exception {

        given(this.journalService.publish(anyObject(), anyObject(), anyLong())).willThrow(new RuntimeException("ERROR"));

        MockMultipartFile mockFile = new MockMultipartFile("file", "data.pdf", "multipart/form-data", "DATADATADATDATADATA".getBytes());

        this.mockMvc.perform(fileUpload("/publisher/publish")
                .file(mockFile)
                .param("name", "JournalTest")
                .param("category", "1"))
                .andExpect(MockMvcResultMatchers.flash().attribute("message", "You failed to publish JournalTest => ERROR"))
                .andExpect(redirectedUrl("/publisher/publish"));

    }
}

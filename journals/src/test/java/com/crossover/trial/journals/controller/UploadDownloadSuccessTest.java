package com.crossover.trial.journals.rest;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithUserDetails;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
public class UploadDownloadSuccessTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }

    @Test
    @WithUserDetails("publisher1")
    public void aTestUpload() throws Exception {

        MockMultipartFile mockFile = new MockMultipartFile("file", "data.pdf", "multipart/form-data", "DATADATADATDATADATA".getBytes());

        this.mockMvc.perform(fileUpload("/publisher/publish")
                .file(mockFile)
                .param("name", "JournalTest")
                .param("category", "1"))
                .andExpect(redirectedUrl("/publisher/browse"));
    }

    @Test
    @WithUserDetails("publisher1")
    public void bTestDownload() throws Exception {
        int bytes = "DATADATADATDATADATA".getBytes().length;
        MvcResult andReturn = this.mockMvc.perform(get("/view/4")).andExpect(status().isOk()).andReturn();

        assertEquals(bytes, andReturn.getResponse().getContentAsByteArray().length);
        assertEquals("DATADATADATDATADATA", andReturn.getResponse().getContentAsString());
    }
}

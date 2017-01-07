package com.crossover.trial.journals.controller;

import com.crossover.trial.journals.model.User;
import com.crossover.trial.journals.repository.UserRepository;
import java.security.Principal;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import static org.mockito.Matchers.anyLong;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
public class UploadFailTest {

    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Mock
    private Principal principal;
    
    @Mock
    private User user;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }

    @Test   
    @WithMockUser(username="user1", password="user1", roles="USER")
    public void bTestDownload() throws Exception {                
               
       // when(((Authentication) this.principal).getPrincipal()).thenReturn(null);
       when(this.userRepository.findOne(anyLong())).thenReturn(user);
       when(this.user.getSubscriptions()).thenReturn(new ArrayList<>());

        MvcResult andReturn = this.mockMvc.perform(get("/view/1")).andExpect(status().is4xxClientError()).andReturn();

        assertEquals(0, andReturn.getResponse().getContentLength());
    }
}

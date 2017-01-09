package com.crossover.trial.journals.service;

import com.crossover.trial.journals.model.User;
import static org.junit.Assert.assertNotNull;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author newlife
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CurrentUserDetailsServiceTest {
    
    @Autowired
    private UserDetailsService UserDetailsService;

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsernameTest() {
        UserDetailsService.loadUserByUsername("nouser");        
    }
}

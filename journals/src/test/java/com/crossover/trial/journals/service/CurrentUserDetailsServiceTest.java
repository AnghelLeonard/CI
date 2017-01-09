package com.crossover.trial.journals.service;

import org.junit.Test;
import org.junit.runner.RunWith;
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
public class CurrentUserDetailsServiceTest {

    @Autowired
    private UserDetailsService UserDetailsService;

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsernameTest() {
        UserDetailsService.loadUserByUsername("nouser");
    }
}

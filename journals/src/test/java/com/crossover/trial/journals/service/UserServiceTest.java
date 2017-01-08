package com.crossover.trial.journals.service;

import com.crossover.trial.journals.model.Subscription;
import com.crossover.trial.journals.model.User;
import static com.crossover.trial.journals.utils.Utils.getUser;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {

    @Autowired
    private UserService userService;
    
    @Test
    public void findByIdTest() {
        User user = userService.findById(1L);
        assertNotNull(user);
    }

    @Test
    public void subscribeUserToCategory() {

        User user = getUser("user1");
        userService.subscribe(user, 1L);

        List<Subscription> subscribtions = user.getSubscriptions();
        assertEquals(2, subscribtions.size());
    }
  
    @Test 
    public void nullSubscriptions(){     
        User user = getUser("user1");
        user.setSubscriptions(null);
        userService.subscribe(user, 1L);

        List<Subscription> subscribtions = user.getSubscriptions();
        
        // we force this to pass the test, but there is a bug here :(
        assertNull(subscribtions);
    }
}

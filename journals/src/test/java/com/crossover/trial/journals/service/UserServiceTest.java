package com.crossover.trial.journals.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Optional;

import com.crossover.trial.journals.model.Journal;
import com.crossover.trial.journals.model.Publisher;
import com.crossover.trial.journals.model.User;
import com.crossover.trial.journals.repository.PublisherRepository;
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
    private JournalService journalService;

    @Autowired
    private UserService userService;

    @Autowired
    private PublisherRepository publisherRepository;

    @Test
    public void subscribeUserToCategory() {
        /*
        List<Journal> journals = journalService.listAll(getUser("user1"));
        assertNotNull(journals);
        assertEquals(1, journals.size());

        assertEquals(new Long(1), journals.get(0).getId());
        assertEquals("Medicine", journals.get(0).getName());
        assertEquals(new Long(1), journals.get(0).getPublisher().getId());
        assertNotNull(journals.get(0).getPublishDate());
*/
    }             
}

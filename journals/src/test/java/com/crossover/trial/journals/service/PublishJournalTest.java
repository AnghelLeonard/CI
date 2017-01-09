package com.crossover.trial.journals.service;

import com.crossover.trial.journals.Application;
import com.crossover.trial.journals.model.Journal;
import com.crossover.trial.journals.model.Publisher;
import com.crossover.trial.journals.model.User;
import com.crossover.trial.journals.repository.PublisherRepository;
import static com.crossover.trial.journals.utils.Utils.getUser;
import static com.crossover.trial.journals.utils.WiserAssertions.assertReceivedMessage;
import java.util.Optional;
import org.junit.After;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.subethamail.wiser.Wiser;

/**
 *
 * @author Anghel Leonard
 */
//@Ignore // in order to run this test ensure that the SMTP server is not running
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ActiveProfiles("test")
@SpringBootTest(classes = Application.class)
public class PublishJournalTest {

    private Wiser wiser;

    @Autowired
    private SignalSenderService signalSenderService;

    @Autowired
    private JournalService journalService;

    @Autowired
    private PublisherRepository publisherRepository;

    @Before
    public void setUp() throws Exception {
        wiser = new Wiser();
        wiser.setHostname("localhost");
        wiser.setPort(25);
        wiser.start();
    }

    @After
    public void tearDown() throws Exception {
        wiser.stop();
    }

    @Test
    public void test() throws Exception {

        // publish a new journal
        User user = getUser("publisher1");
        Optional<Publisher> p = publisherRepository.findByUser(user);

        Journal journal = new Journal();
        journal.setName("WoowJournal");
        journal.setUuid("SOME_EXTERNAL_ID");
        try {
            journalService.publish(p.get(), journal, 3L);
        } catch (ServiceException e) {
            fail(e.getMessage());
        }

        // send signal
        signalSenderService.sendSignal(3L);

        // sleep 5 seconds in order to let signal to propagate to message borker
        // don't do this in production
        Thread.sleep(5000);

        // assert
        assertReceivedMessage(wiser)
                .from("journals@home.org")
                .to("user1@yahoo.com")
                .withSubject("New journal was published")
                .withContent("New journal published in category 3");
    }
}

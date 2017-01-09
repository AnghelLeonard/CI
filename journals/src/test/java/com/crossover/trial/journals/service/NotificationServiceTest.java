package com.crossover.trial.journals.service;

import com.crossover.trial.journals.Application;
import static com.crossover.trial.journals.utils.WiserAssertions.assertReceivedMessage;
import org.junit.After;
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
public class NotificationServiceTest {

    private Wiser wiser;

    @Autowired
    private NotificationService notificationService;

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
    public void send() throws Exception {

        // send message
        notificationService.notifySubscribers("someone@localhost", "subject", "test");

        // assert
        assertReceivedMessage(wiser)
                .from("journals@home.org")
                .to("someone@localhost")
                .withSubject("subject")
                .withContent("test");
    }
}

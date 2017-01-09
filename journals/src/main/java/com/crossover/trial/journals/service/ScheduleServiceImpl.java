package com.crossover.trial.journals.service;

import com.crossover.trial.journals.repository.JournalRepository;
import com.crossover.trial.journals.repository.UserRepository;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author Anghel Leonard
 */
@Component
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationService notificationService;

    private static final Logger LOG = Logger.getLogger(ScheduleServiceImpl.class.getName());
    private static final SimpleDateFormat DF = new SimpleDateFormat("HH:mm:ss");

    @Override
    @Scheduled(initialDelay = 120000, fixedRate = 30000) // it should run once a day,  but we will set it at 30 seconds
    public void reportAddedJournals() {
        LOG.log(Level.INFO, "--------------------------------------------------------");
        LOG.log(Level.INFO, "Schedule task running: {0}", DF.format(new Date()));
        LOG.log(Level.INFO, "--------------------------------------------------------");

        String[] fetchedJournals = journalRepository.fetchTodayJournals();

        if (fetchedJournals != null && fetchedJournals.length > 0) {
            // Don't use findAll()! These are read only data!
            String[] fetchedUsers = userRepository.fetchAllUsers();
            for (String user : fetchedUsers) {
                // The message should contain journal details, etc, but for brevity I think is ok - 
                // having a detailed email is a straighforward implementation
                notificationService.notifySubscribers(user, "Today journals", "Today journals" + Arrays.toString(fetchedJournals));
            }
        }
    }
}

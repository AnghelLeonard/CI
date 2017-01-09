package com.crossover.trial.journals.service;

import com.crossover.trial.journals.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author Anghel Leonard
 */
@Component
public class SignalReceiverServiceImpl implements SignalReceiverService {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserRepository userRepository;

    @Override
    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receiveSignal(Long categoryId) {
        String[] subscribers = userRepository.fetchSubscribersByCategory(categoryId);

        if (subscribers != null && subscribers.length > 0) {
            for (String subscriber : subscribers) {
                // The message should contain journal details, etc, but for brevity I tihnk is ok - 
                // having a detailed email is a straighforward implementation                               
                notificationService.notifySubscribers(subscriber, "New journal was published", "New journal published in category " + categoryId);
            }
        }
    }

}

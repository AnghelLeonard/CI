package com.crossover.trial.journals.service;

import org.springframework.stereotype.Service;

/**
 *
 * @author Anghel Leonard
 */
@Service
public interface NotificationService {

    public void notifySubscribers(String to, String subject, String text);
}

package com.crossover.trial.journals.service;

import org.springframework.stereotype.Service;

/**
 *
 * @author Anghel Leonard
 */
@Service
public interface SignalSenderService {

    public void sendSignal(Long categoryId);

}

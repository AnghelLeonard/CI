package com.crossover.trial.journals.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author Anghel Leonard
 */
@Component
public class SignalSenderServiceImpl implements SignalSenderService {

    private final JmsTemplate jmsTemplate;

    @Autowired
    public SignalSenderServiceImpl(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void sendSignal(Long categoryId) {
        jmsTemplate.convertAndSend("mailbox", categoryId);
    }

}

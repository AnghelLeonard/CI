package com.crossover.trial.journals.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 *
 * @author Anghel Leonard
 */
@Component
public class NotificationServiceImpl implements NotificationService {
 
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void notifySubscribers(String to, String subject, String text) {

        final Context ctx = new Context();
        ctx.setVariable("name", to);
        ctx.setVariable("msg", text);

        final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");

        try {
            message.setFrom("journals@home.org");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);

            final String htmlContent = this.templateEngine.process("mail/html/journal-email", ctx);
            message.setText(htmlContent, true);
        } catch (MessagingException ex) {
            throw new RuntimeException("Unable to build the e-mail message" + ex.getMessage(), ex);
        }

        try {
            javaMailSender.send(mimeMessage);
        } catch (MailSendException e) {
            throw new RuntimeException("Cannot send e-mail to address: " + to, e);
        }
    }
}

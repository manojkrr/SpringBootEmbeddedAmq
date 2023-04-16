package com.jms.assignment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import static com.jms.assignment.Constants.EMAIL_QUEUE_NAME;


@Component
@Slf4j
public class EmailMessagePublisher {
    private final JmsTemplate jmsTemplate;

    @Autowired
    public EmailMessagePublisher(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public boolean sendEmail(Email email) {
        try{
            jmsTemplate.convertAndSend(EMAIL_QUEUE_NAME, email);
            log.info("Email Sent to AMQ");
            return true;
        } catch (Exception ex) {
            log.error("Failed to send email to AMQ", ex);
            return false;
        }
    }
}

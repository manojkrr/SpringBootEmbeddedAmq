package com.jms.assignment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import static com.jms.assignment.Constants.EMAIL_QUEUE_NAME;

@Component
@Slf4j
public class EmailMessageListener {

    private final EmailMessages emailMessages;

    @Autowired
    public EmailMessageListener(EmailMessages emailMessages){
        this.emailMessages = emailMessages;
    }

    @JmsListener(destination = EMAIL_QUEUE_NAME)
    public void receiveEmail(Email email) {
        log.info("Email received={}", email);
        emailMessages.push(email);
    }
}

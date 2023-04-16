package com.jms.assignment;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmailMessages {

    private final List<Email> jmsMessages;

    public EmailMessages() {
        this.jmsMessages = new ArrayList<>();
    }

    public void push(Email email) {
        jmsMessages.add(email);
    }

    public List<Email> getAllJmsMessages() {
        return jmsMessages;
    }
}

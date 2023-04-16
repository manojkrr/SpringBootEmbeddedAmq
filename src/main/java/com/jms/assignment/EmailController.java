package com.jms.assignment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class EmailController {

    private final EmailMessagePublisher emailMessagePublisher;
    private final EmailMessages emailMessages;

    @Autowired
    public EmailController(EmailMessagePublisher emailMessagePublisher, EmailMessages emailMessages) {
        this.emailMessagePublisher = emailMessagePublisher;
        this.emailMessages = emailMessages;
    }


    @PostMapping("/")
    public String sendEmail(@RequestBody Email email) {
        log.info("Received Email Request from Controller {}", email);
        boolean isMessageSent = emailMessagePublisher.sendEmail(email);
        return isMessageSent ? "SUCCESS" : "FAILED";
    }

    @GetMapping("/")
    @ResponseBody
    public List<Email> getAllMessage() {
        return emailMessages.getAllJmsMessages();
    }
}

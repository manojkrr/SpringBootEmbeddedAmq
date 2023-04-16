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
    public String getAllMessage() {
        List<Email> emails = emailMessages.getAllJmsMessages();
        StringBuilder sb = new StringBuilder();
        sb.append("<table border='1'>");
        sb.append("<tr><th>To</th><th>Body</th></tr>");
        for (Email email : emails) {
            sb.append("<tr>");
            sb.append("<td>").append(email.getTo()).append("</td>");
            sb.append("<td>").append(email.getBody()).append("</td>");
            sb.append("</tr>");
        }
        sb.append("</table>");
        return sb.toString();
    }
}

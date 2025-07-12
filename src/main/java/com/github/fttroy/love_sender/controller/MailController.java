package com.github.fttroy.love_sender.controller;

import com.github.fttroy.love_sender.service.MailService;
import com.github.fttroy.love_sender.service.MessageService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/mail")
public class MailController {

    @Value("${mail.info.receiver}")
    private String receiver;

    @Autowired
    private MailService emailService;

    @Autowired
    private MessageService messageService;

    @GetMapping("/test")
    public void test() throws MessagingException {
        sendScheduledEmail();
    }

    public void sendScheduledEmail() {
        messageService.findFirstTextNotSent().ifPresent(
                text -> {
                    log.info("text: {}", text);
                    String subject = text.getSubject();
                    String message = text.getContent();
                    log.info("subject:{}\n content:{}",subject, message);
                    try {
                        emailService.sendSimpleMessage(receiver, subject, message);
                        messageService.updateMessage(receiver, text.getTextId());
                    } catch (MessagingException e) {
                        log.error("ERROR - sending message to {} with exception: ", receiver, e);
                        log.error("ERROR - exception:{} ", e.getMessage());
                    }
                }
        );
    }
}

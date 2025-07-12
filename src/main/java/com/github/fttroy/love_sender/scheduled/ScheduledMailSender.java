package com.github.fttroy.love_sender.scheduled;

import com.github.fttroy.love_sender.service.MailService;
import com.github.fttroy.love_sender.service.MessageService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ScheduledMailSender {

    @Value("${mail.info.receiver}")
    private String receiver;

    @Autowired
    private MessageService messageService;

    @Autowired
    private MailService emailService;

    @Scheduled(cron = "0 24 19 * * *", zone = "Europe/Rome")
    private void sentMailInMorning(){
        sendScheduledEmail();
    }

    @Scheduled(cron = "${scheduled.cron.evening-time}", zone = "Europe/Rome")
    private void sentMailInEvening(){
        sendScheduledEmail();
    }

    public void sendScheduledEmail() {
        messageService.findFirstTextNotSent().ifPresent(
                text -> {
                    String subject = text.getSubject();
                    String message = text.getContent();
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
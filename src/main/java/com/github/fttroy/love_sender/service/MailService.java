package com.github.fttroy.love_sender.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MailService {

    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MessageService messageService;

    public void sendSimpleMessage(String receiver, String subject, String text) throws MessagingException {
        log.info("START - sending email to {}", receiver);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(sender);
        helper.setTo(receiver);
        helper.setSubject(subject);
        helper.setText(text, true);
        log.info("sending mail:\n sender:{}\n receiver:{}\n subject:{}\n text:{}", sender, receiver, subject, text);
        mailSender.send(message);
        log.info("END - sending mail");
    }
}

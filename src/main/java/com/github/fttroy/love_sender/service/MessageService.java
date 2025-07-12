package com.github.fttroy.love_sender.service;

import com.github.fttroy.love_sender.document.Message;
import com.github.fttroy.love_sender.model.Text;
import com.github.fttroy.love_sender.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class MessageService {

    @Autowired
    private MessageRepository repository;

    public void updateMessage(String emailAdress, Integer textId) {
        log.info("START - update message");
        log.info("textId: {}", textId);
        log.info("emailAddress: {}",emailAdress);
        Optional<Message> messageOpt = repository.findByEmailAdress(emailAdress);
        log.info("START - searching message with email address: {}", emailAdress);
        if (messageOpt.isPresent()) {
            Message messageDb = messageOpt.get();
            log.info("START - message with email adress: {} found: {}", emailAdress, messageDb);
            messageDb.getTexts().stream().filter(
                    text -> textId.equals(text.getTextId())
            ).findFirst().ifPresent(
                    text -> text.setSent(true)
            );
            log.info("updating message:{}", messageDb);
            repository.save(messageDb);
        }
        log.info("END - update message");
    }

    public Optional<Text> findFirstTextNotSent() {
        return repository.findAll().get(0)
                .getTexts().stream().filter(
                        text -> !text.isSent()
                ).findFirst();
    }
}

package com.github.fttroy.love_sender.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
@RestController
@RequestMapping("/ping")
public class PingController {

    @GetMapping("/")
    public void ping() {
        log.info("pinged at {}", ZonedDateTime.now(ZoneId.of("Europe/Rome")).toLocalDateTime());
    }
}

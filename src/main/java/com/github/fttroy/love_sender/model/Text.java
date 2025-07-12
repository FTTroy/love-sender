package com.github.fttroy.love_sender.model;

import lombok.Data;

@Data
public class Text {
    private Integer textId;
    private String subject;
    private String content;
    private boolean sent;
}

package com.bsu.avizhen.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageInfo {
    private Integer chatId;
    private Integer userId;
    private String message;
}

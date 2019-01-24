package com.bsu.avizhen.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageInfoWithoutUserId {
    private Integer chatId;
    private String message;
}

package com.bsu.avizhen.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserToFriend {
    private Integer userId;
    private Integer friendId;

    public UserToFriend(Integer userId, Integer friendId) {
        this.userId = userId;
        this.friendId = friendId;
    }
}

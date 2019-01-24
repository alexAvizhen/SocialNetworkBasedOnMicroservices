package com.bsu.avizhen.dto;

import com.bsu.avizhen.entity.Office;
import com.bsu.avizhen.entity.Project;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Date;


@Getter
@Setter
public class UserDto {

    private Integer id;
    private String name;
    private String surname;
    private String login;
    private String email;
    private String position;
    private Collection<UserDto> friends;
    private String workStartDate;

    public UserDto() {
    }
}

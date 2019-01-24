package com.bsu.avizhen.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EventInfo {
    private Date startDate;
    private Date endDate;
    private String address;

}

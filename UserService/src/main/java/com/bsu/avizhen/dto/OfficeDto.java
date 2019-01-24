package com.bsu.avizhen.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfficeDto {
    private Integer id;
    private String country;
    private String city;
    private String address;

    public OfficeDto() {
    }
}

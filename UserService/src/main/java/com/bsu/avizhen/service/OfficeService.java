package com.bsu.avizhen.service;

import com.bsu.avizhen.entity.Office;

import java.util.List;

public interface OfficeService {
    void createOffice(Office office);

    List<Office> getAllOffices();

    Office getOfficeById(Integer id);

    Office findOfficeByCity(String city);
}

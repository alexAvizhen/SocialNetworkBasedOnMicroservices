package com.bsu.avizhen.dao;

import com.bsu.avizhen.entity.Office;

import java.util.Collection;

public interface OfficeDao {
    void createOffice(Office office);

    Office getOfficeById(Integer id);

    Collection<Office> getAllOffices();

    Office findOfficeByCity(String city);
}

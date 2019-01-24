package com.bsu.avizhen.service.impl;

import com.bsu.avizhen.dao.OfficeDao;
import com.bsu.avizhen.entity.Office;
import com.bsu.avizhen.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeDao officeDao;

    @Autowired
    public OfficeServiceImpl(OfficeDao officeDao) {
        this.officeDao = officeDao;
    }

    @Override
    public void createOffice(Office office) {
        officeDao.createOffice(office);
    }

    @Override
    public List<Office> getAllOffices() {
        return new ArrayList<>(officeDao.getAllOffices());
    }

    @Override
    public Office getOfficeById(Integer id) {
        return officeDao.getOfficeById(id);
    }

    @Override
    public Office findOfficeByCity(String city) {
        return officeDao.findOfficeByCity(city);
    }
}

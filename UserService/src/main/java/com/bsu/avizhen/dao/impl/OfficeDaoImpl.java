package com.bsu.avizhen.dao.impl;

import com.bsu.avizhen.dao.OfficeDao;
import com.bsu.avizhen.entity.Office;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class OfficeDaoImpl implements OfficeDao {

    private static Integer officeIdCounter = 1;
    private static Map<Integer, Office> offices = new HashMap<>();

    @Override
    public void createOffice(Office office) {
        if (office.getId() == null) {
            office.setId(generateId());
        }
        offices.put(office.getId(), office);
    }

    @Override
    public Office getOfficeById(Integer id) {
        return offices.get(id);
    }

    @Override
    public Collection<Office> getAllOffices() {
        return offices.values();
    }

    @Override
    public Office findOfficeByCity(String city) {
        for (Office office : getAllOffices()) {
            if (office.getCity().equals(city)) {
                return office;
            }
        }
        return null;
    }

    private Integer generateId() {
        while (offices.containsKey(officeIdCounter)) {
            officeIdCounter++;
        }
        return officeIdCounter;
    }
}

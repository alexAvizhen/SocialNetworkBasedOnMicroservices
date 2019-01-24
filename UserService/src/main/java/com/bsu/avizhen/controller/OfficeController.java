package com.bsu.avizhen.controller;

import com.bsu.avizhen.dto.OfficeDto;
import com.bsu.avizhen.entity.Office;
import com.bsu.avizhen.service.OfficeService;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.bsu.avizhen.factory.DtoEntityTransformerFactory;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OfficeController {

    private OfficeService officeService;
    private DtoEntityTransformerFactory transformerFactory;

    @Autowired
    public OfficeController(OfficeService officeService, DtoEntityTransformerFactory transformerFactory) {
        this.officeService = officeService;
        this.transformerFactory = transformerFactory;
    }

    @RequestMapping(value = {"/offices"}, method = RequestMethod.GET)
    public Collection<OfficeDto> getAllOffices() {
        return Collections2.transform(officeService.getAllOffices(), new Function<Office, OfficeDto>() {
            @Override
            public OfficeDto apply(Office office) {
                return (OfficeDto) transformerFactory.getDtoEntityTransformer(Office.class).transformEntityToDto(office);
            }
        });
    }

    @RequestMapping(value = {"/offices/{id}"}, method = RequestMethod.GET)
    public OfficeDto getOfficeById(@PathVariable("id") Integer id) {
        Office office = officeService.getOfficeById(id);
        return (OfficeDto) transformerFactory.getDtoEntityTransformer(Office.class).transformEntityToDto(office);
    }
}

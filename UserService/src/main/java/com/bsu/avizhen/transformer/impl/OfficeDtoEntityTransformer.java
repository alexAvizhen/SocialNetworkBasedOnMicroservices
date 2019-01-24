package com.bsu.avizhen.transformer.impl;

import com.bsu.avizhen.dto.OfficeDto;
import com.bsu.avizhen.entity.Office;
import com.bsu.avizhen.transformer.DtoEntityTransformer;
import org.springframework.stereotype.Component;

@Component
public class OfficeDtoEntityTransformer implements DtoEntityTransformer<Office, OfficeDto> {

    @Override
    public Office transformDtoToEntity(OfficeDto officeDto) {
        return null;
    }

    @Override
    public OfficeDto transformEntityToDto(Office office) {
        if (office == null) {
            return null;
        }
        OfficeDto officeDto = new OfficeDto();
        officeDto.setId(office.getId());
        officeDto.setAddress(office.getAddress());
        officeDto.setCity(office.getCity());
        officeDto.setCountry(office.getCountry());
        return officeDto;
    }

    @Override
    public Class getEntityClass() {
        return Office.class;
    }
}

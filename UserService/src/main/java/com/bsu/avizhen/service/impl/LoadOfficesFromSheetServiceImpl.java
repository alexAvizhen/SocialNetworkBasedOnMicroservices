package com.bsu.avizhen.service.impl;

import com.bsu.avizhen.entity.Office;
import com.bsu.avizhen.service.LoadOfficesFromSheetService;
import com.bsu.avizhen.service.OfficeService;
import org.apache.logging.log4j.util.Strings;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoadOfficesFromSheetServiceImpl implements LoadOfficesFromSheetService {

    private final OfficeService officeService;

    @Autowired
    public LoadOfficesFromSheetServiceImpl(OfficeService officeService) {
        this.officeService = officeService;
    }

    @Override
    public int loadOfficesFromSheet(Sheet officeSheet) {
        boolean isProcessingStarted = false;
        int amountOfLoadedOffices = 0;
        for (Row officeRow : officeSheet) {
            if (isProcessingStarted) {
                if (isValidOfficeRow(officeRow)) {
                    createOfficeByRow(officeRow);
                    amountOfLoadedOffices++;
                }
            } else {
                isProcessingStarted = isOfficesHeader(officeRow);
            }
        }
        return amountOfLoadedOffices;

    }

    private void createOfficeByRow(Row officeRow) {
        String country = officeRow.getCell(0).getStringCellValue();
        String city = officeRow.getCell(1).getStringCellValue();
        String address = officeRow.getCell(2).getStringCellValue();
        Office newOffice = new Office();
        newOffice.setCountry(country);
        newOffice.setCity(city);
        newOffice.setAddress(address);
        officeService.createOffice(newOffice);
    }

    private boolean isOfficesHeader(Row officeRow) {
        Cell cell = officeRow.getCell(0);
        return cell != null && "Country".equals(cell.getStringCellValue());
    }

    private boolean isValidOfficeRow(Row officeRow) {
        Cell country = officeRow.getCell(0);
        Cell cityCell = officeRow.getCell(1);
        return country != null && cityCell != null && Strings.isNotBlank(country.getStringCellValue())
                && Strings.isNotBlank(cityCell.getStringCellValue());
    }
}

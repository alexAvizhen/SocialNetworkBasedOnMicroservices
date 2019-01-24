package com.bsu.avizhen.service;

import org.apache.poi.ss.usermodel.Sheet;

public interface LoadOfficesFromSheetService {
    int loadOfficesFromSheet(Sheet usersSheet);
}

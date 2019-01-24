package com.bsu.avizhen.service.impl;

import com.bsu.avizhen.service.LoadDataFromWorkbookService;
import com.bsu.avizhen.service.LoadOfficesFromSheetService;
import com.bsu.avizhen.service.LoadProjectsFromSheetService;
import com.bsu.avizhen.service.LoadUsersFromSheetService;
import com.bsu.avizhen.utils.LoadUsersUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoadDataFromWorkbookServiceImpl implements LoadDataFromWorkbookService {

    private LoadUsersFromSheetService loadUsersFromSheetService;
    private LoadOfficesFromSheetService loadOfficesFromSheetService;
    private LoadProjectsFromSheetService loadProjectsFromSheetService;

    @Autowired
    public LoadDataFromWorkbookServiceImpl(LoadUsersFromSheetService loadUsersFromSheetService, LoadOfficesFromSheetService loadOfficesFromSheetService, LoadProjectsFromSheetService loadProjectsFromSheetService) {
        this.loadUsersFromSheetService = loadUsersFromSheetService;
        this.loadOfficesFromSheetService = loadOfficesFromSheetService;
        this.loadProjectsFromSheetService = loadProjectsFromSheetService;
    }


    @Override
    public int loadDataFromWorkbook(Workbook workbook) {
        int res = 0;
        Sheet officesSheet = workbook.getSheet(LoadUsersUtils.OFFICES_SHEET);
        res += loadOfficesFromSheetService.loadOfficesFromSheet(officesSheet);
        Sheet projectsSheet = workbook.getSheet(LoadUsersUtils.PROJECTS_SHEET);
        res += loadProjectsFromSheetService.loadProjectsFromSheet(projectsSheet);
        Sheet userSheet = workbook.getSheet(LoadUsersUtils.USERS_SHEET);
        res += loadUsersFromSheetService.loadUsersFromSheet(userSheet);
        return res;
    }

}

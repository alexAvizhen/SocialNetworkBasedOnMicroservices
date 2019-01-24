package com.bsu.avizhen.service;

import org.apache.poi.ss.usermodel.Sheet;

public interface LoadProjectsFromSheetService {
    int loadProjectsFromSheet(Sheet projectsSheet);
}

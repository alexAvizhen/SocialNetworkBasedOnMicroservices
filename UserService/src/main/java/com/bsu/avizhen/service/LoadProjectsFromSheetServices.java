package com.bsu.avizhen.service;

import org.apache.poi.ss.usermodel.Sheet;

public interface LoadProjectsFromSheetServices {
    int loadProjectsFromSheet(Sheet usersSheet);
}

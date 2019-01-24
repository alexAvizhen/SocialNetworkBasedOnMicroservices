package com.bsu.avizhen.service;

import org.apache.poi.ss.usermodel.Sheet;

public interface LoadUsersFromSheetService {
    int loadUsersFromSheet(Sheet usersSheet);
}

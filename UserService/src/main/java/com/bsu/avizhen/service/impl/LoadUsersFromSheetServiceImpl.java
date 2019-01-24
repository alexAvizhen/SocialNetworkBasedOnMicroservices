package com.bsu.avizhen.service.impl;

import com.bsu.avizhen.entity.Project;
import com.bsu.avizhen.entity.User;
import com.bsu.avizhen.service.LoadUsersFromSheetService;
import com.bsu.avizhen.service.OfficeService;
import com.bsu.avizhen.service.ProjectService;
import com.bsu.avizhen.service.UserService;
import org.apache.logging.log4j.util.Strings;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LoadUsersFromSheetServiceImpl implements LoadUsersFromSheetService {

    private final UserService userService;
    private ProjectService projectService;
    private OfficeService officeService;
    private DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

    @Autowired
    public LoadUsersFromSheetServiceImpl(UserService userService, ProjectService projectService, OfficeService officeService) {
        this.userService = userService;
        this.projectService = projectService;
        this.officeService = officeService;
    }

    @Override
    public int loadUsersFromSheet(Sheet usersSheet) {
        boolean isProcessingStarted = false;
        int amountOfLoadedUsers = 0;
        for (Row userRow : usersSheet) {
            if (isProcessingStarted) {
                if (isValidUserRow(userRow)) {
                    createUserByRow(userRow);
                    amountOfLoadedUsers++;
                }
            } else {
                isProcessingStarted = isUsersHeader(userRow);
            }
        }
        return amountOfLoadedUsers;
    }

    private boolean isValidUserRow(Row userRow) {
        Cell nameCell = userRow.getCell(0);
        Cell positionCell = userRow.getCell(1);
        return nameCell != null && positionCell != null && Strings.isNotBlank(nameCell.getStringCellValue())
                && Strings.isNotBlank(positionCell.getStringCellValue());
    }

    private boolean isUsersHeader(Row userRow) {
        Cell cell = userRow.getCell(0);
        return cell != null && "Name".equals(cell.getStringCellValue());
    }

    private void createUserByRow(Row userRow) {
        String userName = userRow.getCell(0).getStringCellValue();
        String userSurname = userRow.getCell(1).getStringCellValue();
        String email = userRow.getCell(2).getStringCellValue();
        String userPosition = userRow.getCell(3).getStringCellValue();
        String previousProjectNames = userRow.getCell(4).getStringCellValue();
        String currentProjectName = userRow.getCell(5).getStringCellValue();
        String officeCity = userRow.getCell(6).getStringCellValue();
        String startDateStr = userRow.getCell(7).getStringCellValue();

        User user = new User();
        user.setName(userName);
        user.setSurname(userSurname);
        if (userName != null && userSurname != null) {
            user.setLogin(userSurname.substring(0, 2).toLowerCase() + userName.substring(0, 2).toLowerCase());
        }
        user.setEmail(email);
        user.setPosition(userPosition);

        List<Project> previousProjects = new ArrayList<>();
        if (previousProjectNames != null && !previousProjectNames.isEmpty()) {
            for (String projectName : previousProjectNames.split(",")) {
                Project project = projectService.findProjectByName(projectName);
                if (project != null) {
                    previousProjects.add(project);
                }
            }
        }
        user.setPreviousProjects(previousProjects);
        user.setCurrentProject(projectService.findProjectByName(currentProjectName));
        user.setCurrentOffice(officeService.findOfficeByCity(officeCity));
        Date startDate;
        try {
            startDate = df.parse(startDateStr);
        } catch (ParseException e) {
            startDate = null;
        }
        user.setWorkStartDate(startDate);
        userService.createUser(user);
    }
}

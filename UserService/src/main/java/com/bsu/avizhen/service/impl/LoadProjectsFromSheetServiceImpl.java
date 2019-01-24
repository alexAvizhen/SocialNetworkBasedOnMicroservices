package com.bsu.avizhen.service.impl;

import com.bsu.avizhen.entity.Project;
import com.bsu.avizhen.entity.User;
import com.bsu.avizhen.service.LoadProjectsFromSheetService;
import com.bsu.avizhen.service.ProjectService;
import com.bsu.avizhen.service.UserService;
import org.apache.logging.log4j.util.Strings;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoadProjectsFromSheetServiceImpl implements LoadProjectsFromSheetService {
    private final ProjectService projectService;
    private UserService userService;

    @Autowired
    public LoadProjectsFromSheetServiceImpl(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @Override
    public int loadProjectsFromSheet(Sheet projectsSheet) {
        boolean isProcessingStarted = false;
        int amountOfLoadedProjects = 0;
        for (Row projectRow : projectsSheet) {
            if (isProcessingStarted) {
                if (isValidProjectRow(projectRow)) {
                    createProjectByRow(projectRow);
                    amountOfLoadedProjects++;
                }
            } else {
                isProcessingStarted = isProjectsHeader(projectRow);
            }
        }
        return amountOfLoadedProjects;

    }

    private void createProjectByRow(Row projectRow) {
        String projectName = projectRow.getCell(0).getStringCellValue();
        String[] projectLeadSurnames = projectRow.getCell(1).getStringCellValue().split(",");
        List<User> projectLeads = new ArrayList<>();
        for (String projectLeadSurname : projectLeadSurnames) {
            User lead = userService.getUserBySurname(projectLeadSurname);
            if (lead != null) {
                projectLeads.add(lead);
            }
        }
        Project project = new Project();
        project.setName(projectName);
        project.setProjectLeads(projectLeads);
        projectService.createProject(project);
    }

    private boolean isProjectsHeader(Row projectRow) {
        Cell cell = projectRow.getCell(0);
        return cell != null && "Project name".equals(cell.getStringCellValue());
    }

    private boolean isValidProjectRow(Row projectRow) {
        Cell nameCell = projectRow.getCell(0);
        Cell positionCell = projectRow.getCell(1);
        return nameCell != null && positionCell != null && Strings.isNotBlank(nameCell.getStringCellValue())
                && Strings.isNotBlank(positionCell.getStringCellValue());
    }
}

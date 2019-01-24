package com.bsu.avizhen.service.impl;

import com.bsu.avizhen.dao.ProjectDao;
import com.bsu.avizhen.entity.Project;
import com.bsu.avizhen.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectDao projectDao;

    @Autowired
    public ProjectServiceImpl(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @Override
    public void createProject(Project project) {
        projectDao.createProject(project);
    }

    @Override
    public List<Project> getAllProjects() {
        return new ArrayList<>(projectDao.getAllProjects());
    }

    @Override
    public Project getProjectById(Integer id) {
        return projectDao.findProjectById(id);
    }

    @Override
    public Project findProjectByName(String name) {
        return projectDao.findProjectByName(name);
    }

}

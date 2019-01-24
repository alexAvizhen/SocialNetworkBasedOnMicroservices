package com.bsu.avizhen.service;

import com.bsu.avizhen.entity.Office;
import com.bsu.avizhen.entity.Project;

import java.util.List;

public interface ProjectService {
    void createProject(Project project);

    List<Project> getAllProjects();

    Project getProjectById(Integer id);

    Project findProjectByName(String name);
}

package com.bsu.avizhen.dao;

import com.bsu.avizhen.entity.Project;

import java.util.Collection;

public interface ProjectDao {
    void createProject(Project project);

    Project findProjectById(Integer id);

    Collection<Project> getAllProjects();

    Project findProjectByName(String name);
}

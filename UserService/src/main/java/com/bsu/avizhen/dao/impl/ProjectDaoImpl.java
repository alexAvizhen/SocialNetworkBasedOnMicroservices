package com.bsu.avizhen.dao.impl;

import com.bsu.avizhen.dao.ProjectDao;
import com.bsu.avizhen.entity.Project;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class ProjectDaoImpl implements ProjectDao {

    private static Map<Integer, Project> projects = new HashMap<>();
    private static Integer projectsIdCounter = 1;

    @Override
    public void createProject(Project project) {
        if (project.getId() == null) {
            project.setId(generateId());
        }
        projects.put(project.getId(), project);
    }

    @Override
    public Project findProjectById(Integer id) {
        return projects.get(id);
    }

    @Override
    public Collection<Project> getAllProjects() {
        return projects.values();
    }

    @Override
    public Project findProjectByName(String name) {
        for (Project project : getAllProjects()) {
            if (project.getName().equals(name)) {
                return project;
            }
        }
        return null;
    }

    private Integer generateId() {
        while (projects.containsKey(projectsIdCounter)) {
            projectsIdCounter++;
        }
        return projectsIdCounter;
    }
}

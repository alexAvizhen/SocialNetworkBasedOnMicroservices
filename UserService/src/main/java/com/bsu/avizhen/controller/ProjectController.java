package com.bsu.avizhen.controller;

import com.bsu.avizhen.dto.ProjectDto;
import com.bsu.avizhen.entity.Project;
import com.bsu.avizhen.factory.DtoEntityTransformerFactory;
import com.bsu.avizhen.service.ProjectService;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class ProjectController {

    private ProjectService projectService;
    private DtoEntityTransformerFactory transformerFactory;

    @Autowired
    public ProjectController(ProjectService projectService, DtoEntityTransformerFactory transformerFactory) {
        this.projectService = projectService;
        this.transformerFactory = transformerFactory;
    }

    @RequestMapping(value = {"/projects"}, method = RequestMethod.GET)
    public Collection<ProjectDto> getAllProjects() {
        return Collections2.transform(projectService.getAllProjects(), new Function<Project, ProjectDto>() {
            @Override
            public ProjectDto apply(Project project) {
                return (ProjectDto) transformerFactory.getDtoEntityTransformer(Project.class).transformEntityToDto(project);
            }
        });
    }

    @RequestMapping(value = {"/projects/{id}"}, method = RequestMethod.GET)
    public Object getProjectById(@PathVariable("id") Integer id) {
        Project project = projectService.getProjectById(id);
        return transformerFactory.getDtoEntityTransformer(Project.class).transformEntityToDto(project);
    }
}

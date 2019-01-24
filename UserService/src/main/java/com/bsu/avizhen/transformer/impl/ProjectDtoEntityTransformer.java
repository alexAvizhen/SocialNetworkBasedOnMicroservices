package com.bsu.avizhen.transformer.impl;

import com.bsu.avizhen.dto.ProjectDto;
import com.bsu.avizhen.entity.Project;
import com.bsu.avizhen.transformer.DtoEntityTransformer;
import org.springframework.stereotype.Component;

@Component
public class ProjectDtoEntityTransformer implements DtoEntityTransformer<Project, ProjectDto> {
    @Override
    public Project transformDtoToEntity(ProjectDto projectDto) {
        return null;
    }

    @Override
    public ProjectDto transformEntityToDto(Project project) {
        if (project == null) {
            return null;
        }
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setName(project.getName());
        projectDto.setProjectLeads(project.getProjectLeads());
        return projectDto;
    }

    @Override
    public Class getEntityClass() {
        return Project.class;
    }
}

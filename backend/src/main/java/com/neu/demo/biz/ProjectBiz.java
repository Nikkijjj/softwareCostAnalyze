package com.neu.demo.biz;

import com.neu.demo.entity.Project;
import com.neu.demo.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectBiz {
    @Autowired
    private ProjectMapper projectMapper;
    public void setProjectMapper(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    public List<Project> selectProjects() {
        return this.projectMapper.getProjects();
    }

    public Project selectProjectById(String project_id) {
        return this.projectMapper.getProjectById(project_id);
    }
}

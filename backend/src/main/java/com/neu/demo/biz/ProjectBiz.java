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

    //获取所有项目
    public List<Project> selectProjects() {
        return this.projectMapper.getProjects();
    }

    //根据项目Id获取项目
    public Project selectProjectById(String project_id) {
        return this.projectMapper.getProjectById(project_id);
    }

    //根据项目所属公司名称获取项目
    public List<Project> selectProjectsByCompany(String company) {
        return this.projectMapper.getProjectsByCompany(company);
    }

    //更新项目的ufp, ei_num, eo_num, eq_num, ilf_num, elf_num
    public int updateValues(String project_id, int ufp, int ei_num, int eo_num, int eq_num, int ilf_num, int elf_num) {
        return this.projectMapper.updateUFP(project_id, ufp, ei_num, eo_num, eq_num, ilf_num, elf_num);
    }

    //更新项目的step和status
    public int updateStep(String project_id, double step, String project_status) {
        return this.projectMapper.updateStep(project_id, step,project_status);
    }
}

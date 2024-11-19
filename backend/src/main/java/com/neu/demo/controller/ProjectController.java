package com.neu.demo.controller;

import com.neu.demo.biz.ProjectBiz;
import com.neu.demo.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProjectController {
    @Autowired
    private ProjectBiz projectBiz;
    public void setProjectBiz(ProjectBiz projectBiz) {
        this.projectBiz = projectBiz;
    }

    //获取所有项目
    @RequestMapping("/project/selectAllProjects")
    public Map findProjects(){
        List<Project> list = projectBiz.selectProjects();
        Map res = new HashMap<>();
        res.put("isOK",true);
        res.put("msg","查询所有项目信息成功");
        res.put("projects",list);
        return res;
    }

    //根据项目ID获取项目
    @RequestMapping("/project/selectProjectById")
    public Map findProjectById(String project_id){
        Project project = projectBiz.selectProjectById(project_id);
        Map res = new HashMap<>();
        res.put("isOK",true);
        res.put("msg","查询项目成功");
        res.put("project",project);
        return res;
    }

    //根据项目所属公司获取项目
    @RequestMapping("/project/selectProjectsByCompany")
    public Map findProjectsByCompany(String company){
        List<Project> list = projectBiz.selectProjectsByCompany(company);
        Map res = new HashMap<>();
        res.put("isOK",true);
        res.put("msg","查询租户下项目成功");
        res.put("projects",list);
        return res;
    }
}

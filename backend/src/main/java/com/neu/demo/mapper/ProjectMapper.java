package com.neu.demo.mapper;

import com.neu.demo.entity.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProjectMapper {
    //获取全部项目
    @Select("select * from t_project")
    List<Project> getProjects();

    //按照项目id获取项目
    @Select("select * from t_project where project_id = #{project_id}")
    Project getProjectById(String project_id);

    //按照项目所属公司获取项目
    @Select("select * from t_project where company = #{company}")
    List<Project> getProjectsByCompany(String company);
}

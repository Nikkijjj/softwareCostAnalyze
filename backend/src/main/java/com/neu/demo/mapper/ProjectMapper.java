package com.neu.demo.mapper;

import com.neu.demo.entity.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProjectMapper {
    @Select("select * from t_project")
    List<Project> getProjects();

    @Select("select * from t_project where project_id = #{project_id}")
    Project getProjectById(String project_id);
}

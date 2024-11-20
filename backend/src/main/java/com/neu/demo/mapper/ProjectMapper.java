package com.neu.demo.mapper;

import com.neu.demo.entity.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    //更新项目的ufp，ei_num，eo_num，eq_num，ilf_num，elf_num
    @Update("update t_project set ufp = #{ufp}, ei_num = #{ei_num}, eo_num = #{eo_num}, eq_num = #{eq_num}, ilf_num = #{ilf_num}, elf_num = #{elf_num} where project_id = #{project_id}")
    int updateUFP(@Param("project_id") String project_id, @Param("ufp") int ufp, @Param("ei_num") int ei_num, @Param("eo_num") int eo_num, @Param("eq_num") int eq_num, @Param("ilf_num") int ilf_num, @Param("elf_num") int elf_num);

    //更新项目的step
    @Update("update t_project set step = #{step} where project_id = #{project_id}")
    int updateStep(@Param("project_id") String project_id, @Param("step") double step);
}

package com.neu.demo.mapper;


import com.neu.demo.entity.GSC;
import com.neu.demo.entity.Project;
import com.neu.demo.entity.S;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CostEvaluationMapper {

    @Select("select ufp from t_project where project_id=#{project_id}")
    int selectUFP(@Param("project_id") String project_id);

    @Select("select * from t_project where project_id=#{project_id}")
    Project selectProject(@Param("project_id") String project_id);

    @Update("update t_project set s=#{s_value} where project_id=#{project_id}")
    int updateSValue(@Param("project_id") String project_id, @Param("s_value") Double s_value);

    @Update("update t_project set dfp=#{dfp_num} where project_id=#{project_id}")
    int updateDFPValue(@Param("project_id") String project_id, @Param("dfp_num") Double dfp_num);

    @Insert("insert into t_s (project_id, type, value) values (#{project_id}, #{type}, #{value})")
    int insertCfItem(S cfItem);

    @Insert("INSERT INTO t_dfp (project_id, type, value) VALUES (#{project_id},  #{type}, #{value}) ")
    void saveGSCItem(GSC diDatum);

    @Delete("DELETE FROM t_dfp WHERE project_id = #{project_id}")
    void deleteGSCItemsByProjectId(@Param("project_id") String project_id);

}


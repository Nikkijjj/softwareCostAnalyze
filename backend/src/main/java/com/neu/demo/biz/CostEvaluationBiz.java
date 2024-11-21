package com.neu.demo.biz;


import com.neu.demo.entity.GSC;
import com.neu.demo.entity.Project;
import com.neu.demo.entity.S;
import com.neu.demo.mapper.CostEvaluationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CostEvaluationBiz {
    @Autowired
    private CostEvaluationMapper costEvaluationMapper;

    public int selectUFP(String project_id) {
        int ufp_num = costEvaluationMapper.selectUFP(project_id);
        return ufp_num;
    }

    public Project selectProject(String project_id) {
        Project project_info = costEvaluationMapper.selectProject(project_id);
        return project_info;
    }

    public boolean storeSValue(String project_id, Double s_value) {
        int rowsAffected = costEvaluationMapper.updateSValue(project_id, s_value);
        return rowsAffected > 0;
    }

    public boolean storeDFPValue(String project_id, Double dfp_num) {
        int rowsAffected = costEvaluationMapper.updateDFPValue(project_id, dfp_num);
        return rowsAffected > 0;
    }

    public void saveCfItem(S cfItem) {
        costEvaluationMapper.insertCfItem(cfItem);
    }

    public void insertGSCItem(GSC diDatum) {
        costEvaluationMapper.saveGSCItem(diDatum);
    }

    public void deleteGSCItems(String project_id){
        costEvaluationMapper.deleteGSCItemsByProjectId(project_id);
    }


}

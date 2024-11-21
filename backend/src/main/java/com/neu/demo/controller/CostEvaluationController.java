package com.neu.demo.controller;


import com.neu.demo.biz.CostEvaluationBiz;
import com.neu.demo.entity.GSC;
import com.neu.demo.entity.Project;
import com.neu.demo.entity.UFP;
import com.neu.demo.entity.S;
import com.neu.demo.mapper.CostEvaluationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CostEvaluationController {

    @Autowired
    private CostEvaluationBiz costEvaluationBiz;


    @RequestMapping("/costEvaluation/ufp")
    @ResponseBody  // 表示返回的是 JSON 响应
    public Map<String, Object> searchUFP(@RequestParam String project_id){
        Map<String, Object> result = new HashMap<>();

        // 调用 biz 层的方法查询 ufp_num
        int ufp_num = costEvaluationBiz.selectUFP(project_id);

        if(ufp_num != 0){
            result.put("isOk",true);
            result.put("msg","查询ufp成功！");
            result.put("ufp_num",ufp_num);
        }else {
            result.put("isOk",false);
            result.put("msg","查询课程失败！");
        }
        return result;
    }

    @RequestMapping("/costEvaluation/projectInfo")
    @ResponseBody
    public Map<String, Object> projectInfo(@RequestParam String project_id){
        Map<String, Object> result = new HashMap<>();
        Project project = costEvaluationBiz.selectProject(project_id);

        if(project != null){
            result.put("isOk",true);
            result.put("msg","查询项目信息成功");
            result.put("project",project);
        }else {
            result.put("isOk",false);
            result.put("msg","查询项目信息失败");
        }
        return result;
    }

    @RequestMapping ("/costEvaluation/storeS")
    @ResponseBody
    public Map<String, Object> storeS(@RequestParam String project_id, @RequestParam Double s_value){
        Map<String, Object> result = new HashMap<>();
        boolean isStored = costEvaluationBiz.storeSValue(project_id, s_value);

        System.out.println("项目ID"+project_id);
        System.out.println("sValue"+s_value);

        if(isStored){
            result.put("isOk",true);
            result.put("msg","S值存储成功");
        }else {
            result.put("isOk",false);
            result.put("msg","S值存储失败");
        }
        return result;
    }

    @RequestMapping ("/costEvaluation/storeStep")
    @ResponseBody
    public Map<String, Object> storeStep(@RequestParam String project_id, @RequestParam Double step){
        Map<String, Object> result = new HashMap<>();
        boolean isStored = costEvaluationBiz.storeStep(project_id, step);

        System.out.println("项目ID"+project_id);
        System.out.println("step："+step);

        if(isStored){
            result.put("isOk",true);
            result.put("msg","项目进度存储成功");
        }else {
            result.put("isOk",false);
            result.put("msg","项目进度存储失败");
        }
        return result;
    }

    @RequestMapping("/costEvaluation/storeDFP")
    @ResponseBody
    public Map<String, Object> storeDFP(@RequestParam String project_id, @RequestParam Double dfp_num){
        Map<String, Object> result = new HashMap<>();
        boolean isStored = costEvaluationBiz.storeDFPValue(project_id, dfp_num);

        System.out.println("dfp_num:"+dfp_num);

        if(isStored){
            result.put("isOk",true);
            result.put("msg","DFP值存储成功");
        }else {
            result.put("isOk",false);
            result.put("msg","DFP值存储失败");
        }
        return result;
    }

    @RequestMapping("/costEvaluation/saveCfItem")
    @ResponseBody
    public Map<String, Object> saveCfItem(@RequestBody S cfItem) {
        Map<String, Object> result = new HashMap<>();

        // 直接使用传入的cfItem对象
        this.costEvaluationBiz.saveCfItem(cfItem);

        System.out.println("111");

        result.put("isOk", true);
        result.put("msg", "CF项存储成功");
        return result;
    }

    @RequestMapping("/costEvaluation/saveGSCItem")
    @ResponseBody
    public Map<String, Object> saveGSCItem(@RequestBody List<GSC> diData) {
        Map<String, Object> result = new HashMap<>();

        if (diData != null && !diData.isEmpty()) {
            String project_id = diData.get(0).getProject_id(); // 获取第一项的 project_id

            // 调用服务层方法来删除旧的 GSC 项
            costEvaluationBiz.deleteGSCItems(project_id);

            // 调用服务层方法来保存新的 GSC 项
            for (GSC diDatum : diData) {
                costEvaluationBiz.insertGSCItem(diDatum);
            }

            result.put("isOk", true);
            result.put("msg", "GSC项存储成功");
        } else {
            result.put("isOk", false);
            result.put("msg", "没有提供有效的 GSC 项");
        }
        return result;
    }
    //存储模块相关的ufp信息
    @RequestMapping("/costEvaluation/insertUfpInfo")
    public Map<String, Object> insertUFPInfo(@RequestBody List<UFP> ufpItems){
        Map<String, Object> map = new HashMap<>();
        this.costEvaluationBiz.deleteUFP(ufpItems.get(0).getModule_id());
        for (UFP item : ufpItems) {
            System.out.println("Processing UFP item: " + item.getModule_id());
            costEvaluationBiz.insertUFP(item);
        }
        map.put("isOk", true);
        map.put("msg", "模块未调整功能点信息存储成功！");
        return map;
    }

    //加载模块相关的ufp信息
    @RequestMapping("/costEvaluation/loadUfpInfo")
    public Map<String, Object> loadUFPInfo(@RequestParam("module_id") String module_id){
        System.out.println("module_id: " + module_id);
        List<UFP> ufps = costEvaluationBiz.selectModuleUfp(module_id);
        Map res = new HashMap<>();
        res.put("isOK",true);
        res.put("msg", "加载模块相关的ufp信息成功");
        res.put("ufps", ufps);
        return res;

    }
}









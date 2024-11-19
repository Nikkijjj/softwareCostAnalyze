package com.neu.demo.controller;

import com.neu.demo.biz.StructureBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StructureController {
    @Autowired
    private StructureBiz structureBiz;
    public void setStructureBiz(StructureBiz structureBiz) {
        this.structureBiz = structureBiz;
    }

    //获取节点的父节点（获取节点的parent_id，再根据id查到父节点）

    //获取节点的名称

    //获取节点的描述

    //更新节点的信息（名称、描述）

    //添加新节点(模块id由项目id拼接而成，后面的功能点变量初始设置为0)

    //删除节点

    //获取所有功能结构

    //根据功能模块id获取选中功能模块的信息
}

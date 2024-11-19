<template>
    <div class="button-container">
      <el-button @click="switchState">{{ state === 'readOnly' ? '编辑' : '完成' }}</el-button>
    </div>
  
    <div style="width: 500px; height: 300px;margin: 20px;">
    <vue3-tree-org
        :data="treeData" 
        center
        :horizonal="true" 
        :collapsable="false"
        :disabled="state === 'readOnly'"
        :node-draggable="false"
        @on-node-click="handleNodeClick"
        @on-node-delete="handleNodeDelete"
        @contextmenu.prevent
        @on-contextmenu=""
    >
   
      </vue3-tree-org>
  </div>
  
  
    <el-card style="width: 500px; height: 40%; margin: 20px;">
      <template #header>
        <div class="card-header">
          <el-input v-model="selectedModule.module_name" :disabled="state === 'readOnly'" style="width: 400px" placeholder="功能模块名称"/>
        </div>
      </template>
      <el-input v-model="selectedModule.module_desc" :disabled="state === 'readOnly'"  style="width: 400px" :rows="5" type="textarea" placeholder="功能模块描述"/><br/>
      <div class="button-container">
        <el-button @click="save" :disabled="state === 'readOnly'">保存</el-button>
      </div>
    </el-card>
    
  </template>
  
  <script setup lang="ts">
    import {ref, reactive, watch, onMounted} from 'vue';
    import axios from 'axios'; 
  
    interface Node {
      parent: Node,
      id: string,
      label: string,
      children: Node[],
    }
  
    interface Module {
      module_id: string,
      module_name: string,
      module_desc: string,
      parent_id: string,
    }
  
    const projectJson = ref('');
    const currentProject = ref(null);
  
    const treeData = ref<Node>({
        id: '',
        label: '',
        children: [],
        parent:null,
    });
    const modules = ref<Module[]>([]);
  
    const selectedModule = ref(''); 
  
    const state = ref('readOnly');
  
    // 获取当前项目
    const loadCurrentProject = () => {
        const projectStorage = localStorage.getItem('project');
        projectJson.value = projectStorage;
        currentProject.value = JSON.parse(projectJson);
    }
  
    // 获取所有功能结构
    const fetchModules = async() =>{
      const res = await axios.get('/api/structure/getModules');
      if(res.data.isOk)
        modules.value = res.data.modules;
    }
    // 获取一级功能结构
    const fetchModules_1 = async(project_id: string) =>{
        const res = await axios.get('/api/structure/getModules',{params:{project_id:project_id, parent_id:project_id}});
        return res.data.modules;
    }
    // 获取二级功能结构
    const fetchModules_2 = async(project_id: string, module_id: string) => {
        const res = await axios.get('/api/structure/getModules',{params:{project_id:project_id, parent_id:module_id}});
        return res.data.modules;
    }
  
    // 获取当前功能模块
    const fetchModule = async(module_id: string) => {
      const res = await axios.get('/api/structure/getModuleById',{params:{module_id:module_id}});
      return res.data.module;
    }
  
    // 获取节点的父节点id
    const fetchParentNode = async (module_id: string) => {
      const res = await axios.get('/api/structure/getparentId',{params:{module_id:module_id}});
      return res.data.parent_id;
    }
  
    // 获取节点名称
    const fetchModuleName = async (module_id: string) => {
      const res = await axios.get('/api/structure/getModuleName',{params:{module_id:module_id}});
      return res.data.module_name;
    }
  
    // 获取节点功能描述
    const fetchModuleDesc = async (module_id: string) => {
      const res = await axios.get('/api/structure/selectModuleDesc',{params:{module_id:module_id}});
      return res.data.module_desc;
    }
  
    // 构建功能结构图
    const formatTreeChart = async () => {

      const rootNode: Node = {
        id: projectJson.value.projectId,
        label: projectJson.value.projectName,
        children:[],
        parent: null,
      };
  
      // 创建一个 map 存储所有节点，方便根据 parent_id 找到父节点
      const nodeMap: { [key: string]: Node } = {
        [rootNode.id]: rootNode,  // 将根节点加入 map
      };
  
      // 遍历所有功能模块，构建树形结构
      for (const module of modules.value) {
  
        // 如果父节点已经存在于 nodeMap 中，直接使用该节点
        const parentNode = module.parent_id ? nodeMap[module.parent_id] : rootNode;
  
        const moduleNode: Node = {
          id: module.module_id,
          label: module.module_name,
          parent: parentNode,
          children: [],
        };
  
        // 将当前节点添加到父节点的 children 数组中
        parentNode.children.push(moduleNode);
  
        // 将新节点加入到 nodeMap 中，方便下次查找
        nodeMap[module.module_id] = moduleNode;
      }

      treeData.value = rootNode;
      return {
        treeData,
      };
  
    }
  
    // 点击节点获取当前节点信息
    const handleNodeClick = async (node: Node) => {
      console.log('node clicked');
      const module = await fetchModule(node.id);
      selectedModule.value = module;
    };
    const onExpand = () => {
  
    };
    const onNodeDblclick = () => {
  
    }
  
    // 点击按钮切换状态
    const switchState = () => {
      state.value = state.value === 'readOnly' ? 'editable' : 'readOnly';
    }
  
    // 保存节点编辑信息
    const save = async (selectedModule: Module) => {
      const fd = new FormData();
      fd.append('module_id',selectedModule.module_id);
      fd.append('module_name',selectedModule.module_name);
      fd.append('module_desc',selectedModule.module_desc);
      const res = await axios.post('',fd);
  
      if(res.data.isOk){
        formatTreeChart();    
      }
  
    }
  
    // 添加新节点
    const addNode = async (parentNode: Node) => {
      const fd = new FormData();
      fd.append('parent_id', parentNode.id);
      fd.append('project_id',projectJson.value.projectId);
      fd.append('module_name', '新功能模块');
      fd.append('module_nesc', '新功能模块描述');
      const res = await axios.post('/api/structure/addModule',fd);
      const newNode = res.data.module;
      
      selectedModule.value = newNode;
      formatTreeChart();
    }
    const addTreeNode = (parentNode: Node, newNode: Node) => {
      parentNode.children.push(newNode);
    };
  
    // 删除节点
    const deleteNode = async (module_id: string) => {
      const res = await axios.get('/api/structure/deleteModule',{params:{module_id:module_id}});
      
    }
    // 从图中移除节点
    const removeTreeNode = (parentNode: Node, nodeToRemoveId: string) => {
      parentNode.children = parentNode.children.filter(node => node.id !== nodeToRemoveId);
      deleteNode(nodeToRemoveId);
    };
  
    onMounted(async() => {
      loadCurrentProject();
      formatTreeChart();
    })
  
  </script>
    
  <style lang="scss">
  .button-container {
    display: flex;
    justify-content: flex-end; /* 右对齐 */
    padding: 10px; /* 可选，增加内边距 */
  }
  
  .node-card {
    background-color: white;
    border: 1px solid #dcdfe6;
    transition: background-color 0.3s, box-shadow 0.3s;
  }
  
  .node-card.selected {
    background-color: rgba(41, 86, 131, 1);
    color: white; /* 选中后文字颜色 */
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  }
  
  .vue3-tree-org {
    .tree-node {
      .label-wrapper {
        .label {
          font-family: MicrosoftYaHei;
          font-weight: 400;
          font-size: 14px;
          color: rgba(41, 86, 131, 1);
          background-color: #FFFFFF;
        }
      }
    }
  }
  </style>
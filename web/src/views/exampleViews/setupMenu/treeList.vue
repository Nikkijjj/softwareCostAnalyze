<template>
    <div class="tree-container">
        <el-tree ref="treeRef" class="filter-tree" :data="treeData" :props="defaultProps" default-expand-all :filter-node-method="filterNode" @node-click="handleNodeClick"/>
    </div>
</template>

<script lang="ts">
import { onMounted, ref, reactive } from 'vue';
import { ElTree } from 'element-plus';
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

const treeData = ref([]);
const treeRef = ref<InstanceType<typeof ElTree> | null>(null);

const selected_module_id = ref(''); 

const projectJson = ref('');
const currentProject = ref(null);
// 获取当前项目
const loadCurrentProject = () => {
    const projectStorage = localStorage.getItem('project');
    projectJson.value = projectStorage;
    currentProject.value = JSON.parse(projectJson);
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

// 格式化树形组件
const formatTreeData = async () => {
  const rootNode: Node = {
    id: currentProject.value.project_id,
    label: currentProject.value.project_name,
    children: [],
    parent: null,
  };

  const modules_1 = await fetchModules_1(currentProject.value.project_id);
  for (const module_1 of modules_1.value) {
    const modules_2 = await fetchModules_2(currentProject.value.project_id, module_1.module_id);
    const module1Node: Node = {
        id: module_1.module_id,
        label: module_1.module_name,
        children: modules_2.map(module_2 => ({
            id:module_2.module_id,
            label: module_2.module_name,
            children:[],
            parent: null,
        })),
        parent: rootNode,
      };

      for(const module_2 of module1Node.children){
        module_2.parent = module1Node;
      }
      rootNode.children.push(module1Node);        
  }
    treeData.value = [rootNode];
}

// 点击节点
const handleNodeClick = (node:Node) => {
    selected_module_id.value = node.id;
}

onMounted(() => {
    loadCurrentProject();
    formatTreeData();
})

</script>

<style>
.tree-container {
  flex: 0 0 200px; /* 固定宽度 */
  margin-right: 20px;
  height: 600px; /* 设置固定高度 */
  overflow: auto; /* 超出高度时显示滚动条 */
}
</style>
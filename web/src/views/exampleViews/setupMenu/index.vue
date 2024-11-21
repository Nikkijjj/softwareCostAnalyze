<template>
  <div class="page-container main-view">
    <div class="content-container">
      <div class="left-panel">
        <div v-if="currentProject" class="project-info">
          <h2 class="project-name">{{ currentProject.project_name }}</h2>
          <div class="info-row">
            <el-tag :type="tagtype">{{ currentProject.status }}</el-tag>
            <p class="font-size: 1em;">{{ currentProject.company }}</p>
          </div>
          <div class="details">
            <div v-html="currentProject.project_info"></div>
          </div>
          <div class="info-row ddl-row">
            <p class="font-size: 1em;">评估截止日期：{{ currentProject.ddl }}</p>
          </div>
        </div>
        <div v-else>
          <p>加载中...</p>
        </div>
      </div>

      <div class="right-panel">
        <div class="button-container" style="align-self: flex-start;">
          <el-button @click="switchState">{{ state === 'readOnly' ? '进入编辑模式' : '退出编辑模式' }}</el-button>
        </div>
        <div class="tree-container" style="width: 100%; height: 400px;" @contextmenu.prevent>
          <vue3-tree-org :data="treeData" center :horizonal="true" :collapsable="false" :disabled="state === 'readOnly'"
            :node-draggable="false" :define-menus="defineMenus" @on-node-click="handleNodeClick">
            <template #default="{ node }">
              <div class="node-content">
                <span>{{ node.label }}</span>
                <div v-if="state === 'editable'" class="edit-controls">
                  <el-button type="info" :icon="Plus" size="small" @click.stop="addNode(node)" circle />
                  <el-button type="info" :icon="Minus" size="small" @click.stop="deleteNode(node)" circle />
                </div>
              </div>
            </template>
          </vue3-tree-org>
        </div>
        <div class="module-details">
          <el-card>
            <template #header>
              <div class="card-header">
                <el-input v-model="selectedModule.module_name" :disabled="state === 'readOnly'" placeholder="功能模块名称"
                  @input="checkModification" />
              </div>
            </template>
            <el-input v-model="selectedModule.module_desc" :disabled="state === 'readOnly'" rows="5" type="textarea"
              placeholder="功能模块描述" @input="checkModification" />
            <div class="button-container">
              <el-button @click="save()" :disabled="state === 'readOnly'">保存</el-button>
            </div>
          </el-card>
        </div>
      </div>

    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { Plus, Minus } from '@element-plus/icons-vue';
import { ElMessage, ElMessageBox } from 'element-plus';

axios.defaults.withCredentials = true;

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

const tagtype = ref('');

const projectJson = ref('');
const currentProject = ref(null);

const treeData = ref<Node>({
  id: '',
  label: '',
  children: [],
  parent: null,
});

const selectedModule = ref(''); // 当前选中的功能模块

const state = ref('readOnly'); // 只读状态or编辑状态
const isModified = ref(false); // 文本是否有改动

// 获取当前项目
const loadCurrentProject = () => {
  const projectStorage = localStorage.getItem('project');
  projectJson.value = projectStorage;
  currentProject.value = JSON.parse(projectJson);
}

//获取当前页面展示项目的基本信息
async function getProject() {
  //这里需要获取当前页面的项目
  try {
    switch (currentProject.value.status) {
      case '已评估':
        tagtype.value = 'success';
        break;
      case '未评估':
        tagtype.value = 'danger';
        break;
      default:
        tagtype.value = 'warning';
    }

  } catch (error) {
    ElMessage.error('API请求错误');
  }
}

// 获取一级功能结构
const fetchModules_1 = async (project_id: string) => {
  const parent_id = project_id;
  const res = await axios.get('http://localhost:9000/structure/getModulesUnderParent', {
    params: { project_id: project_id, parent_id: project_id }
  });
  if (res.data.isOK) {
    return res.data.modules;
  } else {
    ElMessage({ message: res.data.msg, type: 'error' });
  }
}
// 获取二级功能结构
const fetchModules_2 = async (project_id: string, module_id: string) => {
  const res = await axios.get('http://localhost:9000/structure/getModulesUnderParent', { params: { project_id: project_id, parent_id: module_id } });
  if (res.data.isOK) {
    return res.data.modules;
  } else {
    ElMessage({ message: res.data.msg, type: 'error' });
  }
}

// 获取当前功能模块
const fetchModule = async (module_id: string) => {
  const res = await axios.get('http://localhost:9000/structure/getModuleById', { params: { module_id: module_id } });
  if (res.data.isOK) {
    return res.data.structure;
  } else {
    ElMessage({ message: res.data.msg, type: 'error' });
  }
}

// 格式化树形组件
const formatTreeChart = async () => {
  // 初始化根节点
  const rootNode: Node = {
    id: currentProject.value.project_id,
    label: currentProject.value.project_name,
    children: [],
    parent: null,
  };

  // 获取一级模块
  const modules_1 = await fetchModules_1(currentProject.value.project_id);
  rootNode.children = await Promise.all(
    modules_1.map(async (module_1) => {
      const module1Node: Node = {
        id: module_1.module_id,
        label: module_1.module_name,
        children: [],
        parent: rootNode,
      };

      // 获取二级模块并添加到一级模块中
      const modules_2 = await fetchModules_2(currentProject.value.project_id, module_1.module_id);
      module1Node.children = modules_2.map((module_2) => ({
        id: module_2.module_id,
        label: module_2.module_name,
        children: [],
        parent: module1Node,
      }));

      return module1Node;
    })
  );

  treeData.value = rootNode;
};


// 禁用右键菜单
const defineMenus = (e: Event, node: Node) => { };

// 点击节点获取当前节点信息
const handleNodeClick = async (e: Event, node: Node) => {
  //console.log('node clicked');
  const module = await fetchModule(node.id);
  selectedModule.value = { ...module };
};

// 监视文本是否有改动
const checkModification = () => {
  isModified.value = true;
};

// 点击按钮切换状态
const switchState = () => {
  if (isModified.value) {
    ElMessageBox.confirm(
      '您有未保存的修改，是否放弃修改并退出编辑模式？',
      '确认退出',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }
    ).then(() => {
      // 用户确认退出，重置修改状态并切换状态
      isModified.value = false;
      state.value = state.value === 'readOnly' ? 'editable' : 'readOnly';
    }).catch(() => {
      // 用户取消操作，什么都不做
    });
  } else {
    // 如果没有修改，直接切换状态
    state.value = state.value === 'readOnly' ? 'editable' : 'readOnly';
  }
};

// 保存节点编辑信息
const save = async () => {
  const module = selectedModule.value;
  const res = await axios.get('http://localhost:9000/structure/updateModule', {
    params: {
      module_id: module.module_id,
      module_name: module.module_name,
      module_desc: module.module_desc,
    }
  });

  if (res.data.isOK) {
    ElMessage({ message: res.data.msg, type: 'success' });
    formatTreeChart();
    isModified.value = false;
  } else {
    ElMessage({ message: res.data.msg, type: 'error' });
  }
}

// 添加新节点 （三级节点后不能继续加
const addNode = async (parentNode: Node) => {
  const lastLevel = await isLastLevel(parentNode);

  if (!lastLevel) {
    const res = await axios.get('http://localhost:9000/structure/addModule', {
      params: {
        parent_id: parentNode.id,
        project_id: projectJson.value.project_id,
        module_name: '新功能模块',
        module_desc: '新功能模块描述',
      }
    });
    if (res.data.isOK) {
      const newNode: Node = {
        id: res.data.module.module_id,
        label: res.data.module.module_name,
        children: [],
        parent: parentNode,
      };
      parentNode.children.push(newNode);

      ElMessage({ message: res.data.msg, type: 'success' });

      selectedModule.value = newNode;
      console.log("111", selectedModule.value.id);
      formatTreeChart();
    } else {
      ElMessage({ message: res.data.msg, type: 'error' });
    }
  }
}

// 删除节点 （根节点不能被删除
const deleteNode = async (node: Node) => {
  const res = await axios.get('http://localhost:9000/structure/deleteModule', { params: { module_id: node.id } });
  if (res.data.isOK) {
    node.children = node.children.filter(n => n.id !== node.id);
    ElMessage({ message: res.data.msg, type: 'success' });
    formatTreeChart();
  } else {
    ElMessage({ message: res.data.msg, type: 'error' });
  }

};

// 是否为三级节点
const isLastLevel = async (node: Node) => {
  const res = await axios.get('http://localhost:9000/structure/isLeaf', { params: { module_id: node.id } });
  if (res.data.isOK) {
    ElMessage({ message: res.data.msg, type: 'error' });
    return true;
  } else {
    return false;
  }
}

onMounted(async () => {
  await getProject();
  await loadCurrentProject();
  await formatTreeChart();
})
</script>


<style lang="scss" scoped>
.main-view {
  display: flex;
  flex-direction: row;
  padding: 10px;

  >.content-container {
    flex: 1;
    display: flex;
    box-sizing: border-box;
  }

  .left-panel {
    flex: 1;
    padding: 10px;
    background: #fff;
    margin-right: 15px;
    border-radius: 8px;
    overflow-y: auto;
  }

  .right-panel {
    width: 45%;
    display: flex;
    flex-direction: column;
    gap: 20px;
    background: #fff;
    border-radius: 8px;
    padding: 20px;

    .tree-container {
      flex: 1;
      width: 100%;
      height: 300px;
    }

    .module-details {
      flex: 1;
      display: flex;
      flex-direction: column;
      gap: 15px;

      .card-header {
        display: flex;
        justify-content: center;
        margin-bottom: 10px;
      }

      .button-container {
        display: flex;
        justify-content: flex-end;
      }
    }
  }

}

.project-info {
  padding: 20px;
  background: #fff;
  border-radius: 8px;

  .project-name {
    text-align: center;
    font-weight: bold;
    font-size: 1.6em;
    margin-bottom: 20px;
  }

  .info-row {
    display: flex;
    justify-content: space-between;
    padding: 5px 0;
  }

  .ddl-row {
    justify-content: flex-end;
    margin-bottom: 15px;
  }

  .details {
    margin-top: 20px;

    p {
      margin: 10px 0;
      font-size: 0.95em;
    }
  }
}
</style>

<style lang="scss">
.button-container {
  display: flex;
  justify-content: center;
  padding: 10px;
}

.node-card {
  background-color: white;
  border: 1px solid #dcdfe6;
  transition: background-color 0.3s, box-shadow 0.3s;
}


.node-card.selected {
  background-color: rgba(41, 86, 131, 1);
  color: white;
  /* 选中后文字颜色 */
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

.node-controls {
  margin-top: 5px;
  display: flex;
  gap: 5px;
}

.add-btn,
.remove-btn {
  width: 20px;
  height: 20px;
  border: none;
  background-color: null;
  color: #000000;
  border-radius: 50%;
  font-size: 16px;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
}

button:hover {
  opacity: 0.8;
}
</style>
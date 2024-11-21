<template>
    <div class="tree-container">
        <el-tree
            ref="treeRef"
            class="filter-tree"
            :data="treeData"
            :props="defaultProps"
            default-expand-all
            @node-click="handleNodeClick"
            highlight-current
            :indent="50"
            :show-checkbox="false"
            node-key="id"
        >
            <template #default="{ node, data }">
                <span v-if="!node.isLeaf" style="display: flex; align-items: center">
                    <!-- 手动插入箭头 -->
                    <i
                        v-if="!node.isLeaf"
                        :class="['custom-arrow', node.expanded ? 'expanded' : '']"
                    ></i>
                    <el-icon v-if="node.expanded" style="margin: 0 6px 0 0px" size="16"
                        ><FolderOpened
                    /></el-icon>
                    <el-icon v-else style="margin: 0 6px 0 0px" size="16"><Folder /></el-icon>
                    <small>{{ node.label }}</small>
                </span>

                <span v-else style="display: flex; align-items: center">
                    <el-icon style="margin: 0 6px 0 0px" size="16"><Document /></el-icon>
                    <small>{{ node.label }}</small>
                </span>
            </template>
        </el-tree>
    </div>
</template>

<script lang="ts" setup>
import { onMounted, ref, defineProps, defineEmits } from 'vue';
import axios from 'axios';
import { Folder, FolderOpened, Document } from '@element-plus/icons-vue';
import type { ElTree } from 'element-plus';

interface Node {
    id: string;
    label: string;
    children: Node[];
    module: Module | null; // 存储完整的 module 对象
}

interface Module {
    module_id: string;
    module_name: string;
    module_desc: string;
    parent_id: string;
    project_id: string;
    ufp: number;
    ei_num: number;
    eo_num: number;
    eq_num: number;
    ilf_num: number;
    elf_num: number;
    step: number;
}

const treeData = ref<Node[]>([]);
const treeRef = ref<InstanceType<typeof ElTree> | null>(null); //

defineProps({});
const emit = defineEmits(['node-clicked']); // 定义事件

const currentProject = ref<any>(null);

// 加载当前项目
const loadCurrentProject = async (project_id: string) => {
    try {
        const res = await axios.get('http://localhost:9000/project/selectProjectById', {
            params: { project_id },
        });

        if (res.data && res.data.project) {
            currentProject.value = res.data.project;
        } else {
            console.warn('未找到项目数据');
        }
    } catch (error) {
        console.error('加载项目失败：', error);
    }
};

// 获取功能模块数据
const fetchModules = async (project_id: string, parent_id: string): Promise<Module[]> => {
    const res = await axios.get('http://localhost:9000/structure/getModulesUnderParent', {
        params: { project_id, parent_id },
    });
    return res.data.modules || [];
};

// 构建树结构
const formatTreeData = async () => {
    const rootNode: Node = {
        id: currentProject.value.project_id,
        label: currentProject.value.project_name,
        children: [],
        module: null, // 根节点没有具体模块对象
    };

    const modules_1 = await fetchModules(
        currentProject.value.project_id,
        currentProject.value.project_id,
    );

    for (const module_1 of modules_1) {
        const modules_2 = await fetchModules(currentProject.value.project_id, module_1.module_id);

        const module1Node: Node = {
            id: module_1.module_id,
            label: module_1.module_name,
            children: modules_2.map((module_2) => ({
                id: module_2.module_id,
                label: module_2.module_name,
                children: [],
                module: module_2, // 叶子节点存储完整的 module 对象
            })),
            module: module_1,
        };

        rootNode.children.push(module1Node);
    }

    treeData.value = [rootNode];
    // 找到第一个叶子节点
    const firstLeafNode = findFirstLeafNode(treeData.value);
    if (firstLeafNode) {
        treeRef.value?.setCurrentKey(firstLeafNode.id); // 设置选中节点
        if (firstLeafNode.module) {
            handleNodeClick(firstLeafNode); // 触发节点点击事件
        }
    }
};

// 点击节点
const handleNodeClick = (node: Node) => {
    if (node.children.length === 0 && node.module) {
        // 点击叶子节点时触发，传递完整的 module 对象
        emit('node-clicked', node.module,"leaf");
    } else {
        emit('node-clicked',null,"other");
    }
};

// 找到第一个叶子节点
const findFirstLeafNode = (nodes: Node[]): Node | null => {
    for (const node of nodes) {
        if (node.children.length === 0) {
            return node; // 找到叶子节点
        }
        const leafNode = findFirstLeafNode(node.children);
        if (leafNode) {
            return leafNode;
        }
    }
    return null;
};

// 挂载时加载数据
onMounted(async () => {
    await loadCurrentProject('2');
    await formatTreeData();
});
</script>

<style lang="scss" scoped>
.custom-arrow {
    display: inline-block;
    width: 0;
    height: 0;
    margin-right: 15px;
    border-left: 5px solid transparent;
    border-right: 5px solid transparent;
    border-top: 7px solid #606266; /* 上箭头 */

    &.expanded {
        border-top: none;
        border-bottom: 7px solid #606266; /* 下箭头 */
    }
}
.tree-container {
    ::v-deep(.el-tree) {
        .el-tree-node {
            // 所有节点
            i.el-tree-node__expand-icon {
                padding: 5px;
                svg {
                    display: none; // 隐藏所有节点的 svg 图标
                }
            }

            // 已展开的父节点
            i.el-tree-node__expand-icon.expanded {
                transform: rotate(0deg); // 取消旋转
                -webkit-transform: rotate(0deg); // 取消旋转
            }

            .el-tree-node__content {
                display: flex;
                align-items: center; /* 垂直居中 */
                gap: 8px; /* 元素间距 */
                small {
                    font-size: 14px;
                }
            }
        }
    }
}
</style>

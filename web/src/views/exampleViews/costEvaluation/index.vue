<template>
    <el-card style="height: 34vh" class="box-card">
        <el-row style="height: 100%; display: flex">
            <!-- 树组件 -->
            <el-col :span="6" style="height: 100%; display: flex; align-items: stretch">
                <div class="element-plus-tree">
                    <StrctureTree @node-clicked="handleNodeClick" />
                </div>
            </el-col>

            <!-- 右侧内容 -->
            <el-col :span="16" style="height: 100%">
                <el-card
                    style="width: 58vw; height: 30vh; border-radius: 5px; margin-left: 23px"
                    shadow="hover"
                >
                    <template #header>
                        <div class="card-header">
                            <span>“{{ currentModule?.module_name || '' }}”模块功能需求</span>
                            <div class="statusTag">
                                <el-tag
                                    size="large"
                                    type="success"
                                    v-if="currentModule && currentModule.step === 0.5"
                                    >评估中</el-tag
                                >
                                <el-tag
                                    size="large"
                                    type="info"
                                    v-if="currentModule && currentModule.step === 0"
                                    >未评估</el-tag
                                >
                                <el-tag
                                    size="large"
                                    type="warning"
                                    v-if="currentModule && currentModule.step === 1"
                                    >已评估</el-tag
                                >
                            </div>
                        </div>
                    </template>
                    <el-text style="margin-left: 10px; margin-top: 20px" size="large">{{
                        currentModule?.module_desc
                    }}</el-text>
                </el-card>
            </el-col>
        </el-row>
    </el-card>
    <el-card class="box-card">
        <el-row>
            <el-col :span="6">
                <div style="height: 40vh; max-width: 300px; margin-left: 20px; margin-top: 10px">
                    <el-steps class="steps-bar" :active="currentStep" direction="vertical">
                        <el-step
                            title="计算系统未调整功能点数(IFPUG法)"
                            @click="currentStep = 0"
                            description="请根据功能描述分模块识别功能点，并将不同功能点的具体描述填入相应文本框内。若未识别出某类功能点，请空置相应文本框。"
                            :status="step1Status"
                        />
                        <el-step
                            title="计算系统已调整功能点数(通用系统调整因子法)"
                            @click="currentStep = 1"
                            :status="step2Status"
                        />
                        <el-step
                            title="计算系统未调整功能点数(需求变更调整因子法)"
                            @click="currentStep = 2"
                            :status="step3Status"
                        />
                    </el-steps>
                </div>
            </el-col>
            <el-col :span="18">
                <!-- 动态内容容器 -->
                <div v-show="currentStep === 0" class="scroll-container">
                    <IFPUG v-model="ufpFormRef" />
                </div>
                <div v-show="currentStep === 1" class="scroll-container">
                    <GSC ref="gscComponent" :GSCForm="GSCForm" />
                </div>
                <div v-show="currentStep === 2" class="scroll-container">
                    <CF ref="cfComponent" :CfForm="CfForm" />
                </div>
                <div class="button-container">
                    <el-button class="opr-btn" @click.prevent="saveModuleEva()">保存</el-button>
                    <el-button class="opr-btn" @click.prevent="submitModuleEva">提交</el-button>
                    <el-button class="opr-btn" v-if="p_step == 0.5">进入GSC评估</el-button>
                    <el-button class="opr-btn" v-if="p_step == 0.8">进入CF评估</el-button>
                </div>
            </el-col>
        </el-row>
    </el-card>
</template>

<script setup lang="ts">
import { onMounted, reactive, ref, computed, watch, toRefs, watchEffect } from 'vue';
import GSC from './components/gsc.vue';
import CF from './components/cf.vue';
import IFPUG from './components/ifpug.vue';
import StrctureTree from './components/treeList.vue';
import axios from 'axios';
import { ElMessage } from 'element-plus';
//CF对象和接口

interface CfItem {
    project_id: string;
    s_id: number;
    type: string;
    value: number;
}

const CfForm = reactive<{
    cf: CfItem;
    ufp: number;
}>({
    cf: {
        project_id: '',
        s_id: 0,
        type: 'cf',
        value: 0,
    },
    ufp: 0,
});
//GSC对象和接口
interface GSCItem {
    project_id: string;
    gsc_id: number;
    type: string;
    value: number;
}
const GSCForm = reactive<{
    gsc: GSCItem;
    ufp: number;
    di: GSCItem[];
}>({
    gsc: {
        project_id: '',
        gsc_id: 0,
        type: 'gsc',
        value: 0,
    },
    ufp: 0,
    di: [
        {
            project_id: '2',
            gsc_id: 0,
            type: '数据通信',
            value: 0,
        },
        {
            project_id: '2',
            gsc_id: 0,
            type: '分布式数据处理',
            value: 0,
        },
        {
            project_id: '2',
            gsc_id: 0,
            type: '性能',
            value: 0,
        },
        {
            project_id: '2',
            gsc_id: 0,
            type: '重度配置',
            value: 0,
        },
        {
            project_id: '2',
            gsc_id: 0,
            type: '处理速率',
            value: 0,
        },
        {
            project_id: '2',
            gsc_id: 0,
            type: '在线数据输入',
            value: 0,
        },
        {
            project_id: '2',
            gsc_id: 0,
            type: '最终用户使用效率',
            value: 0,
        },
        {
            project_id: '2',
            gsc_id: 0,
            type: '在线升级',
            value: 0,
        },
        {
            project_id: '2',
            gsc_id: 0,
            type: '复杂处理',
            value: 0,
        },
        {
            project_id: '2',
            gsc_id: 0,
            type: '可重用性',
            value: 0,
        },
        {
            project_id: '2',
            gsc_id: 0,
            type: '易安装性',
            value: 0,
        },
        {
            project_id: '2',
            gsc_id: 0,
            type: '易操作性',
            value: 0,
        },
        {
            project_id: '2',
            gsc_id: 0,
            type: '多场所',
            value: 0,
        },
        {
            project_id: '2',
            gsc_id: 0,
            type: '支持变更',
            value: 0,
        },
    ],
});
//UFP对象和接口
interface UfpItem {
    module_id: string;
    ufp_id: number;
    type: string;
    detail: string;
}
const ufpForm = reactive<{
    module_id: string;
    ilfs: UfpItem[];
    elfs: UfpItem[];
    eis: UfpItem[];
    eos: UfpItem[];
    eqs: UfpItem[];
    complexity: UfpItem;
    ufp: number;
    m_step: number; //模块功能点评估进度
}>({
    module_id: '',
    ilfs: [
        {
            module_id: '',
            ufp_id: 1,
            type: 'ilf',
            detail: '',
        },
    ],
    elfs: [
        {
            module_id: '',
            ufp_id: 1,
            type: 'elf',
            detail: '',
        },
    ],
    eis: [
        {
            module_id: '',
            ufp_id: 1,
            type: 'ei',
            detail: '',
        },
    ],
    eos: [
        {
            module_id: '',
            ufp_id: 1,
            type: 'eo',
            detail: '',
        },
    ],
    eqs: [
        {
            module_id: '',
            ufp_id: 1,
            type: 'eq',
            detail: '',
        },
    ],
    complexity: {
        module_id: '',
        ufp_id: 1,
        type: 'complexity',
        detail: '',
    },
    ufp: 0,
    m_step: 0,
});
const ufpFormRef = toRefs(ufpForm);
const currentStep = ref(0);
const p_step = ref<number>(0); //项目评估进度
const currentModule = ref<any>(null);
const currentIsLeaf = ref();

onMounted(async () => {
    try {
        const response = await axios.get(
            'http://localhost:9000/costEvaluation/projectInfo?project_id=2',
        );
        CfForm.cf.project_id = response.data.project.project_id;
        GSCForm.gsc.project_id = response.data.project.project_id;
        CfForm.ufp = response.data.project.ufp;
        GSCForm.ufp = response.data.project.ufp;
        p_step.value = response.data.project.step;
        
        console.log("CfForm.cf.project_id"+CfForm.cf.project_id);
        console.log('项目信息：', response.data);
        console.log('UFP1：', CfForm.ufp);
        console.log('UFP2：', GSCForm.ufp);
    } catch (error) {
        console.log('获取项目信息失败', error);
    }
});

const cfComponent = ref(null);
const gscComponent = ref(null);

const saveModuleEva = async () => {
    //保存所有表单信息
    if (cfComponent.value) {
        console.log('CF保存' + cfComponent.value);
        cfComponent.value.storeSValue();
        cfComponent.value.sendCfItemToBackend();
        console.log('保存S值成功！');
    } else {
        console.log('未找到 S 值组件！');
    }
    console.log('GSC保存' + gscComponent.value);
    if (gscComponent.value) {
        gscComponent.value.storeDFPValue();
        gscComponent.value.sendGSCValuesToBackend(); //保存的方法
        console.log('保存GSC值成功！');
    } else {
        console.log('未找到 GSC 值组件！');
    }
    saveModuleUFPEva(1);
    //检测并保存项目评估进度
    catchGSCandCFStep()
    //更新p_step
    //
  
};

const catchGSCandCFStep = async () => {
    // 检查 GSC 表单是否完整
    if (gscComponent.value.isGSCComplete&&cfComponent.value.isCFComplete) {
        // 如果2.3表单完整，设置 step 为 0.9
        p_step.value = 0.9;
    } else if(gscComponent.value.isGSCComplete&&!cfComponent.value.isCFComplete||!gscComponent.value.isGSCComplete&&cfComponent.value.isCFComplete) {
        // 如果表单有一项未完整，设置 step 为 0.8
        p_step.value = 0.8;
    } else if(!gscComponent.value.isGSCComplete&&!cfComponent.value.isCFComplete) {
        // 如果表单2.3均未填完，不设置 step
        //p_step.value = 0.5;
        return;
    } 

};

const submitModuleEva = () => {
    
    if(p_step.value===0.9){
        saveModuleEva();
        //发送请求更新项目进度为1
        //
    }else if (p_step.value<0.9){
        ElMessage.warning("您尚未评估完成，请继续评估。注意保存全部信息后再提交！");
    }
};

//根据点击状态和项目评估进度设置步骤条状态
const step1Status = computed(() => {
    if (p_step.value >= 0.5) {
        return 'success';
    }
    if (currentStep.value === 0) {
        return 'process';
    }
    return 'wait'; // 默认状态
});
const step2Status = computed(() => {
    if (p_step.value >= 0.8) {
        return 'success';
    }
    if (currentStep.value === 1) {
        return 'process';
    }
    return 'wait'; // 默认状态
});
const step3Status = computed(() => {
    if (p_step.value === 1) {
        return 'success';
    }
    if (currentStep.value === 2) {
        return 'process';
    }
    return 'wait'; // 默认状态
});
//树形组件点击处理
function handleNodeClick(module: any, type: string) {
    if (type == 'leaf') {
        console.log('选中的模块:', module);
        currentModule.value = module;
        currentIsLeaf.value = true;
        ufpFormRef.module_id.value = module.module_id;
        //加载模块对应的ufp信息
        axios
            .get('http://localhost:9000/costEvaluation/loadUfpInfo', {
                params: { module_id: currentModule.value.module_id },
            })
            .then((response) => {
                console.log('后端返回的数据:', response.data.ufps);
                classifyUfpItems(response.data.ufps); // 分类处理数据
            })
            .catch((error) => {
                console.error('获取数据失败:', error);
            });
    } else {
        currentModule.value = module;
        currentIsLeaf.value = false;
    }
}
// 数据分类方法
function classifyUfpItems(items: UfpItem[]) {
    const types = ['ilf', 'elf', 'ei', 'eo', 'eq'];
    types.forEach((type) => {
        ufpFormRef[type + 's'].value = items.filter((item) => item.type === type);
        if (ufpFormRef[type + 's'].value.length === 0) {
            ufpFormRef[type + 's'].value.push({
                module_id: currentModule.value.module_id,
                ufp_id: 1,
                type: type,
                detail: '',
            });
        }
    });

    ufpFormRef.complexity.value = items.find((item) => item.type === 'complexity') || {
        module_id: currentModule.value.module_id,
        ufp_id: 1,
        type: 'complexity',
        detail: '',
    };
}
//保存模块ufp
function saveModuleUFPEva(m_step: number) {
    if (currentModule.value == null) {
        ElMessage.error('未选中功能模块！');
        return;
    } else {
        //发送模块未调整功能点信息到后端
        console.log('父组件中ufp：' + String(ufpFormRef.ufp.value));
            let fd = new FormData();
            fd.append('module_id', currentModule.value.module_id); // 假设是字符串
            fd.append('project_id', currentModule.value.project_id); // 假设是字符串
            fd.append('parent_id', currentModule.value.parent_id); // 假设是字符串
            fd.append('module_name', currentModule.value.module_name); // 假设是字符串
            fd.append('module_desc', currentModule.value.module_desc); // 假设是字符串
            fd.append('ufp', String(ufpFormRef.ufp.value)); // 将 number 转为 string
            fd.append('ei_num', String(ufpFormRef.eis.value.filter((item) => item.detail).length)); // 转为 string
            fd.append('eo_num', String(ufpFormRef.eos.value.filter((item) => item.detail).length)); // 转为 string
            fd.append('eq_num', String(ufpFormRef.eqs.value.filter((item) => item.detail).length)); // 转为 string
            fd.append(
                'ilf_num',
                String(ufpFormRef.ilfs.value.filter((item) => item.detail).length),
            ); // 转为 string
            fd.append(
                'elf_num',
                String(ufpFormRef.elfs.value.filter((item) => item.detail).length),
            ); // 转为 string
            fd.append('step', String(m_step)); // 将 step 转为 string
            axios
                .post('http://localhost:9000/structure/updateUFPInfo', fd)
                .then((res) => {
                    if (res.data.isOk) {
                        ElMessage.success('保存未调整功能点数信息成功！');
                    } else {
                        ElMessage.error('保存未调整功能点数信息失败！');
                    }
                })
                .catch(() => {
                    ElMessage.error('发送模块未调整功能点信息失败，请稍后重试');
                });
            //发送ufp详细信息到后端
            const mergedUfpData = [
                ...ufpFormRef.ilfs.value.filter((item) => item.detail), // 过滤有 detail 的数据
                ...ufpFormRef.elfs.value.filter((item) => item.detail),
                ...ufpFormRef.eis.value.filter((item) => item.detail),
                ...ufpFormRef.eos.value.filter((item) => item.detail),
                ...ufpFormRef.eqs.value.filter((item) => item.detail),
                ufpFormRef.complexity.value, // complexity 是一个单独对象，直接加入数组
            ];
            axios
                .post('http://localhost:9000/costEvaluation/insertUfpInfo', mergedUfpData)
                .then((res) => {
                    if (res.data.isOk) {
                        console.log('模块未调整功能点信息保存成功！');
                    } else {
                        console.log('模块未调整功能点信息保存失败！');
                    }
                })
                .catch(() => {
                    ElMessage.error('发送数据失败，请稍后重试');
                });
    }
    //发送请求，要求项目根据结构表更新自身信息
    axios
        .get('http://localhost:9000/project/updateValues', {
            params: { project_id: '2' },
        })
        .then((response) => {
            console.log(response.data.msg);
        })
        .catch((error) => {
            console.error('获取数据失败:', error);
        });
    axios
        .get('http://localhost:9000/project/updateStep', {
            params: { project_id: '2' },
        })
        .then((response) => {
            console.log(response.data.msg);
        })
        .catch((error) => {
            console.error('获取数据失败:', error);
        });
}
</script>

<style scoped lang="scss">
.box-card {
    margin-left: 8px;
    margin-top: 10px;
    width: 82vw;
    height: 52vh;
}
.element-plus-tree {
    height: 30vh; /* 固定高度以确保滚动条显示 */
    overflow-y: auto; /* 垂直方向启用滚动条 */
    padding-right: 10px; /* 避免滚动条遮挡内容 */
    margin-left: 0;
}
.scroll-container {
    margin-left: 20px;
    height: 45vh; /* 固定高度以确保滚动条显示 */
    overflow-y: auto; /* 垂直方向启用滚动条 */
    padding-right: 10px; /* 避免滚动条遮挡内容 */
}
.button-container {
    display: flex; /* 使用 Flex 布局 */
    justify-content: flex-end; /* 按钮靠右对齐 */
    align-items: center;
}
.opr-btn {
    margin-left: 8px; /* 按钮之间的间距 */
}
.statusTag {
    display: inline-block;
    margin-left: auto; /* 将标签推到右侧 */
}
.card-header {
    display: flex; /* 使用 Flex 布局 */
    justify-content: space-between; /* 两端对齐 */
    align-items: center; /* 垂直居中 */
}
</style>

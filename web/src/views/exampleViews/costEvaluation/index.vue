<template>
    <el-row style="height: 35vh">
        <el-col :span="12"><div class="grid-content ep-bg-purple" /></el-col>
        <el-col :span="12"><div class="grid-content ep-bg-purple-light" /></el-col>
    </el-row>
    <el-row style="height: 55vh">
        <el-card class="box-card">
            <el-row>
                <el-col :span="6">
                    <div style="height: 47vh">
                        <el-steps
                            class="steps-bar"
                            :active="currentStep"
                            finish-status="success"
                            direction="vertical"
                        >
                            <el-step title="IFPUG法" @click="currentStep = 0" />
                            <el-step title="GSC法" @click="currentStep = 1" />
                            <el-step title="规模变更调整因子法" @click="currentStep = 2" />
                        </el-steps>
                    </div>
                </el-col>
                <el-col :span="18">
                    <!-- 动态内容容器 -->
                    <div v-show="currentStep === 0" class="scroll-container">
                        <IFPUG v-model="ufpForm"/>
                    </div>
                    <div v-show="currentStep === 1" class="scroll-container">
                        <GSC ref="gscComponent" :GSCForm="GSCForm"/>
                    </div>
                    <div v-show="currentStep === 2" class="scroll-container">
                        <CF ref="cfComponent" :CfForm="CfForm"/>
                    </div>
                    <div class="button-container">
                        <el-button class="opr-btn" @click.prevent="pre">上一步</el-button>
                        <el-button class="opr-btn" @click.prevent="next">下一步</el-button>
                        <el-button class="opr-btn" @click.prevent="saveModuleEva">保存</el-button>
                        <el-button class="opr-btn" @click.prevent="submitModuleEva">提交</el-button>
                    </div>
                </el-col>
            </el-row>
        </el-card>
    </el-row>
</template>

<script setup lang="ts">
import { onMounted, reactive, ref, watch, watchEffect } from 'vue';
import GSC from './components/gsc.vue';
import CF from './components/cf.vue';
import IFPUG from './components/ifpug.vue';
import axios from 'axios';
import Cf from './components/cf.vue';
import { ElMessage } from 'element-plus';

interface UfpItem {
    module_id: string;
    ufp_id: number;
    type: string;
    detail: string;
}
const ufpForm = reactive<{
    ilfs: UfpItem[];
    elfs: UfpItem[];
    eis: UfpItem[];
    eos: UfpItem[];
    eqs: UfpItem[];
    complexity: UfpItem;
    ufp:number;
}>({
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
    ufp:0
});

interface CfItem {
  project_id: string;
  dfp_id: number;
  type: string;
  value: number;
}

const CfForm = reactive<{
  cf: CfItem;
  ufp: number;
}>({
  cf: {
    project_id: '',
    dfp_id: 0,
    type: 'cf',
    value: 0,
  },
  ufp: 0,
})

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
        }
    ],
})


const currentStep = ref(0);
const p_step = ref(0);

onMounted(async () => {
    try {
        const response = await axios.get('http://localhost:9000/costEvaluation/projectInfo?project_id=1');
        CfForm.ufp = response.data.project.ufp;
        GSCForm.ufp = response.data.project.ufp;
        console.log('项目信息：', response.data);
        console.log('UFP1：', CfForm.ufp);
        console.log('UFP2：', GSCForm.ufp);
    } catch (error) {
        console.log('获取项目信息失败', error);   
    }
});

const cfComponent = ref(null);
const gscComponent = ref(null);

// watchEffect(() => gscComponent.value.isGSCComplete, (isComplete) => {
//     if (isComplete) {
//         p_step.value = 0.8;
//     } else {
//         p_step.value = 0.5;
//     }
// });

const saveModuleEva = async () => {
  try {
    const response = await axios.get('http://localhost:9000/costEvaluation/projectInfo?project_id=1');
    console.log('save里的项目信息：', response.data);

    if (response.data.project && response.data.project.step) {

      const step = response.data.project.step;

      if (step === 0.5) {
        if (cfComponent.value) {
          console.log("CF保存" + cfComponent.value);
          cfComponent.value.storeSValue();
          cfComponent.value.sendCfItemToBackend();
          console.log('保存S值成功！');
        } else {
          console.log('未找到 S 值组件！');
        }
      } else if (step === 0.8) {
        console.log("GSC保存" + gscComponent.value);
        if (gscComponent.value) {
        //   gscComponent.value.storeDFPValue();
          gscComponent.value.sendGSCValuesToBackend();//保存的方法
          console.log('保存GSC值成功！');
        } else {
          console.log('未找到 GSC 值组件！');
        }
      } else if (step === 0) {
        // 调用其他函数，暂时为空
        console.log('UFP保存');
      }
    }
  } catch (error) {
    console.log('获取项目信息失败', error);
    ElMessage.error('获取项目信息失败: ' + error.message);
  }
};

const catchGSCStep = async () => {

      // 检查 GSC 表单是否完整
      if (gscComponent.value.isGSCComplete) {
          // 如果表单完整，设置 step 为 0.8
        p_step.value = 0.8;
      } else {
        // 如果表单未完整，设置 step 为 0.5
        p_step.value = 0.5;
      }
};

const submitModuleEva = () => {
    console.log('提交');
};  

const next = () => {
    if (currentStep.value < 2) {
        // 最多到第 2 步
        currentStep.value++;
    }
};

const pre = () => {
    if (currentStep.value > 0) {
        // 最多到第 0 步
        currentStep.value--;
    }
};
</script>

<style scoped lang="scss">
.box-card {
    margin-top: 20px;
    width: 82vw;
}
.scroll-container {
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
</style>

<template>
    <el-form class="gsc-form" :model="dynamicValidateForm" label-width="auto" style="max-width: 800px">
        <div class="form-container">
            <div class="form-group1">
                <!-- 1 -->
                <el-form-item label="数据通信">
                    <el-select v-model="dynamicValidateForm.di[0].value" placeholder="请选择DI值">
                        <el-option label="0" value="0" />
                        <el-option label="1" value="1" />
                        <el-option label="2" value="2" />
                        <el-option label="3" value="3" />
                        <el-option label="4" value="4" />
                        <el-option label="5" value="5" />
                    </el-select>
                </el-form-item>
                <!-- 2 -->
                <el-form-item label="分布式数据处理">
                    <el-select v-model="dynamicValidateForm.di[1].value" placeholder="请选择DI值">
                        <el-option label="0" value="0" />
                        <el-option label="1" value="1" />
                        <el-option label="2" value="2" />
                        <el-option label="3" value="3" />
                        <el-option label="4" value="4" />
                        <el-option label="5" value="5" />
                    </el-select>
                </el-form-item>
                <!-- 3 -->
                <el-form-item label="性能">
                    <el-select v-model="dynamicValidateForm.di[2].value" placeholder="请选择DI值">
                        <el-option label="0" value="0" />
                        <el-option label="1" value="1" />
                        <el-option label="2" value="2" />
                        <el-option label="3" value="3" />
                        <el-option label="4" value="4" />
                        <el-option label="5" value="5" />
                    </el-select>
                </el-form-item>
                <!-- 4 -->
                <el-form-item label="重度配置">
                    <el-select v-model="dynamicValidateForm.di[3].value" placeholder="请选择DI值">
                        <el-option label="0" value="0" />
                        <el-option label="1" value="1" />
                        <el-option label="2" value="2" />
                        <el-option label="3" value="3" />
                        <el-option label="4" value="4" />
                        <el-option label="5" value="5" />
                    </el-select>
                </el-form-item>
                <!-- 5 -->
                <el-form-item label="处理速率">
                    <el-select v-model="dynamicValidateForm.di[4].value" placeholder="请选择DI值">
                        <el-option label="0" value="0" />
                        <el-option label="1" value="1" />
                        <el-option label="2" value="2" />
                        <el-option label="3" value="3" />
                        <el-option label="4" value="4" />
                        <el-option label="5" value="5" />
                    </el-select>
                </el-form-item>
            </div>
            <div class="form-group2">
                <!-- 6 -->
                <el-form-item label="在线数据输入">
                    <el-select v-model="dynamicValidateForm.di[5].value" placeholder="请选择DI值">
                        <el-option label="0" value="0" />
                        <el-option label="1" value="1" />
                        <el-option label="2" value="2" />
                        <el-option label="3" value="3" />
                        <el-option label="4" value="4" />
                        <el-option label="5" value="5" />
                    </el-select>
                </el-form-item>
                <!-- 7 -->
                <el-form-item label="最终用户使用效率">
                    <el-select v-model="dynamicValidateForm.di[6].value" placeholder="请选择DI值">
                        <el-option label="0" value="0" />
                        <el-option label="1" value="1" />
                        <el-option label="2" value="2" />
                        <el-option label="3" value="3" />
                        <el-option label="4" value="4" />
                        <el-option label="5" value="5" />
                    </el-select>
                </el-form-item>
                <!-- 8 -->
                <el-form-item label="在线升级">
                    <el-select v-model="dynamicValidateForm.di[7].value" placeholder="请选择DI值">
                        <el-option label="0" value="0" />
                        <el-option label="1" value="1" />
                        <el-option label="2" value="2" />
                        <el-option label="3" value="3" />
                        <el-option label="4" value="4" />
                        <el-option label="5" value="5" />
                    </el-select>
                </el-form-item>
                <!-- 9 -->
                <el-form-item label="复杂处理">
                    <el-select v-model="dynamicValidateForm.di[8].value" placeholder="请选择DI值">
                        <el-option label="0" value="0" />
                        <el-option label="1" value="1" />
                        <el-option label="2" value="2" />
                        <el-option label="3" value="3" />
                        <el-option label="4" value="4" />
                        <el-option label="5" value="5" />
                    </el-select>
                </el-form-item>
                <!-- 10 -->
                <el-form-item label="可重用性">
                    <el-select v-model="dynamicValidateForm.di[9].value" placeholder="请选择DI值">
                        <el-option label="0" value="0" />
                        <el-option label="1" value="1" />
                        <el-option label="2" value="2" />
                        <el-option label="3" value="3" />
                        <el-option label="4" value="4" />
                        <el-option label="5" value="5" />
                    </el-select>
                </el-form-item>
            </div>
            <div class="form-group3">
                
                <!-- 11 -->
                <el-form-item label="易安装性">
                    <el-select v-model="dynamicValidateForm.di[10].value" placeholder="请选择DI值">
                        <el-option label="0" value="0" />
                        <el-option label="1" value="1" />
                        <el-option label="2" value="2" />
                        <el-option label="3" value="3" />
                        <el-option label="4" value="4" />
                        <el-option label="5" value="5" />
                    </el-select>
                </el-form-item>
                <!-- 12 -->
                <el-form-item label="易操作性">
                    <el-select v-model="dynamicValidateForm.di[11].value" placeholder="请选择DI值">
                        <el-option label="0" value="0" />
                        <el-option label="1" value="1" />
                        <el-option label="2" value="2" />
                        <el-option label="3" value="3" />
                        <el-option label="4" value="4" />
                        <el-option label="5" value="5" />
                    </el-select>
                </el-form-item>
                <!-- 13 -->
                <el-form-item label="多场所">
                    <el-select v-model="dynamicValidateForm.di[12].value" placeholder="请选择DI值">
                        <el-option label="0" value="0" />
                        <el-option label="1" value="1" />
                        <el-option label="2" value="2" />
                        <el-option label="3" value="3" />
                        <el-option label="4" value="4" />
                        <el-option label="5" value="5" />
                    </el-select>
                </el-form-item>
                <!-- 14 -->
                <el-form-item label="支持变更">
                    <el-select v-model="dynamicValidateForm.di[13].value" placeholder="请选择DI值">
                        <el-option label="0" value="0" />
                        <el-option label="1" value="1" />
                        <el-option label="2" value="2" />
                        <el-option label="3" value="3" />
                        <el-option label="4" value="4" />
                        <el-option label="5" value="5" />
                    </el-select>
                </el-form-item>
            </div>
        </div>
        <div class="calculateDFP-container">
            <el-input
                v-model="di"
                style="max-width: 600px"
                placeholder="点击计算总DI值"
                class="input-with-select"
            >
                <template #prepend>
                    <el-button type="primary" @click="onSubmit">计算总DI值</el-button>
                </template>
            </el-input>

            <el-input
                v-model="dfp_num"
                style="max-width: 600px"
                placeholder="点击计算DFP值"
                class="input-with-select"
            >
                <template #prepend>
                    <el-button type="primary" @click="calculateDFP">计算DFP值</el-button>
                </template>
            </el-input>
        </div>
        <el-alert type="info" show-icon :closable="false" style="margin-top:5px;">
            <p>
                计算公式：DFP=(UFP+CFP)*[(∑DI*0.01)+0.65]
            </p>
        </el-alert>
    </el-form>
</template>

<script lang="ts" setup>

import { computed, reactive, onMounted } from 'vue';
import {ref, watch} from 'vue';
import axios, { AxiosResponse } from 'axios';
import { ElMessage } from 'element-plus';
import { defineProps, defineExpose } from 'vue';
import { el } from 'element-plus/es/locales.mjs';

const form = reactive({
    di1: -1,
    di2: -1,
    di3: -1,
    di4: -1,
    di5: -1,
    di6: -1,
    di7: -1,
    di8: -1,
    di9: -1,
    di10: -1,
    di11: -1,
    di12: -1,
    di13: -1,
    di14: -1,
});

  let di = ref(0);

  let dfp_num = ref(0);

  const props = defineProps<{
    GSCForm: {
        ufp: number;
    }
  }>()

  const isGSCComplete = computed(() => {
    return dynamicValidateForm.di.every( (item) => item.value !== -1) && di.value !== 0 && dfp_num.value !== 0;
  })

// const { GSCForm } = props;

interface GSCItem {
    project_id: string;
    gsc_id: number;
    type: string;
    value: number;
}

const dynamicValidateForm = reactive<{
    gsc: GSCItem;
    ufp: number;
    di: GSCItem[];
}>({
    gsc: {
        project_id: '1',
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
            value: form.di1,
        },
        {
            project_id: '2',
            gsc_id: 0,
            type: '分布式数据处理',
            value: form.di2,
        },
        {
            project_id: '2',
            gsc_id: 0,
            type: '性能',
            value: form.di3,
        },
        {
            project_id: '2',
            gsc_id: 0,
            type: '重度配置',
            value: form.di4,
        },
        {
            project_id: '2',
            gsc_id: 0,
            type: '处理速率',
            value: form.di5,               
        },
        {
            project_id: '2',
            gsc_id: 0,
            type: '在线数据输入',
            value: form.di6,
        },
        {
            project_id: '2',
            gsc_id: 0,
            type: '最终用户使用效率',
            value: form.di7,
        },
        {
            project_id: '2',
            gsc_id: 0,
            type: '在线升级',
            value: form.di8,
        },
        {
            project_id: '2',
            gsc_id: 0,
            type: '复杂处理',
            value: form.di9,
        },
        {
            project_id: '2',
            gsc_id: 0,
            type: '可重用性',
            value: form.di10,
        },
        {
            project_id: '2',
            gsc_id: 0,
            type: '易安装性',
            value: form.di11,
        },
        {
            project_id: '2',
            gsc_id: 0,
            type: '易操作性',
            value: form.di12,
        },
        {
            project_id: '2',
            gsc_id: 0,
            type: '多场所',
            value: form.di13,
        },
        {
            project_id: '2',
            gsc_id: 0,
            type: '支持变更',
            value: form.di14,
        }
    ],
})

onMounted(async () => {
  const project_id = dynamicValidateForm.gsc.project_id;
  try {
    // 调用后端API获取数据
    const response = await axios.get(`http://localhost:9000/costEvaluation/getGSCItem`, {params: {project_id: project_id}});

    console.log(response.data);
    // 检查响应数据是否存在
    if (response.data.isOk) {
      response.data.gsc.forEach(item => {
        const diItem = dynamicValidateForm.di.find(di => di.type === item.type);
        if (diItem) {
          diItem.value = item.value;
        }
      });
    }
  } catch (error) {
    console.error('Error fetching GSC data:', error);
  }
});

const onSubmit = () => {
    console.log("form"+dynamicValidateForm.di[0].value);
    // 获取所有 DI 值的和，防止 null 或 undefined 的情况
    const sum = [dynamicValidateForm.di[0].value, dynamicValidateForm.di[1].value, dynamicValidateForm.di[2].value, dynamicValidateForm.di[3].value, dynamicValidateForm.di[4].value, dynamicValidateForm.di[5].value, dynamicValidateForm.di[6].value, dynamicValidateForm.di[7].value, dynamicValidateForm.di[8].value, dynamicValidateForm.di[9].value, dynamicValidateForm.di[10].value, dynamicValidateForm.di[11].value, dynamicValidateForm.di[12].value, dynamicValidateForm.di[13].value]
      .map(Number) // 转换为数字，避免字符串加法
      .filter(val => val !== -1 && !isNaN(val)) // 过滤掉无效值
      .reduce((acc, val) => acc + val, 0); // 计算和

    // 更新 input3 的值
    di.value = sum;
  }

//   const calculateDFP = (ufp) => {
//     console.log("进来");
    
//   // 确保 di.value 是数字
//   const diValue = parseFloat(di.value);
//   // 确保 ufp 是数字
//   const ufpValue = parseFloat(ufp);

//   // 计算 VAF
//   const VAF = (diValue * 0.01) + 0.65;
//   // 计算 DFP 并返回结果，确保结果是数字
//   console.log(ufpValue);
//   console.log(diValue);
  
  
//   return (ufpValue * VAF);
// }

const calculateDFP = () => {
  // 确保 di.value 是数字
  const diValue = di.value;
  console.log("props",props);
  // 确保 ufp 是数字
  const ufpValue = props.GSCForm.ufp;

  // 计算 VAF
  const VAF = (diValue * 0.01) + 0.65;

  // 计算 DFP 并更新 dfp_num 的值
  dfp_num.value = ufpValue * VAF;
  console.log("UFP值:", ufpValue);
  console.log("DI值:", diValue);
  console.log("DFP值:", dfp_num.value);
};


//   const fetchUFP = async () => {
//     try {
//       // 发送 GET 请求到后端获取 ufp
//       // const response = await axios.get(`http://localhost:9000/costEvaluation/ufp?project_id=${project_id}`);
//       const response = await axios.get(`http://localhost:9000/costEvaluation/ufp?project_id=1`);

//       // 检查后端是否返回了成功的状态
//       if (response.data.isOk) {
        
//         // 成功获取到 UFP 值
//         //更新di.value的值
        
//         const sum = [form.di1, form.di2, form.di3, form.di4, form.di5, form.di6, form.di7, form.di8, form.di9, form.di10, form.di11, form.di12, form.di13, form.di14]
//       .map(Number) // 转换为数字，避免字符串加法
//       .filter(val => !isNaN(val)) // 过滤掉无效值
//       .reduce((acc, val) => acc + val, 0); // 计算和

//         // 更新 input3 的值
//         di.value = sum;
//         ufp = response.data.ufp_num;
//         console.log("UFP值:", ufp);

//         console.log(form)
//         // 调用 calculateDFP 方法计算 DFP 值
//         dfp_num.value = calculateDFP();
//         console.log("DFP值:", dfp_num);
        
    
        
//       } else {
//         console.log("后端返回失败:", response.data.msg);
//       }
//     } catch (error) {
//       // 处理错误
//       if (error.response) {
//         // 请求已发出，但服务器响应的状态码不在 2xx 范围内
//         console.log("获取 UFP 失败:", error.response.status);
//         console.log("错误信息:", error.response.data);
//       } else if (error.request) {
//         // 请求已发出但没有收到回应
//         console.log("请求已发出但没有收到回应");
//       } else {
//         // 发送请求时出了点问题
//         console.log("获取 UFP 失败:", error.message);
//       }
//     }
// };

const storeDFPValue = async () => {
    try {
        const params = {
            project_id: dynamicValidateForm.gsc.project_id,
            dfp_num: dfp_num.value,
        }

        const response = await axios.get(`http://localhost:9000/costEvaluation/storeDFP`, { params });

        console.log("dfp_num", dfp_num.value);
        console.log("project_id", dynamicValidateForm.gsc.project_id);
        console.log("存储DFP响应数据", response.data);

        if (response.data.isOk) {
            ElMessage.success("DFP 值存储成功");
        } else {
            ElMessage.error("DFP 值存储失败:" + response.data.msg);
        }
    } catch (error) {
        ElMessage.error("DFP 值存储失败:" + error.message);
    }
};

const sendGSCValuesToBackend = async () => {
    console.log("form",form);
    
    
    try {
     const diData = dynamicValidateForm.di.map(item => {
        console.log("item",item);

        //确保item.value不是null或undefined
        if(item.value !== null && item.value !== undefined){
            return {
                project_id: item.project_id,
                type: item.type,
                value: item.value
            };
            
        }
        return null;
      }).filter(item => item !== null);
      console.log("diData", diData);
      

      const response = await axios.post(`http://localhost:9000/costEvaluation/saveGSCItem`, diData);

      if (response.data.isOk) {
        console.log("GSC 数据上传成功");
      } else {
        console.log("GSC 数据上传失败:", response.data.msg);
      }
    }catch (error) {
      if (error.response) {
        console.log("GSC 数据上传失败:", error.response.status);
        console.log("错误信息:", error.response.data);
      }
    }
}

watch(() => di.value, (newValue) => {
  // 将 newValue 转换为数字
  const diValue = newValue;
  // console.log("===========");
}, { immediate: true });

defineExpose({ sendGSCValuesToBackend, storeDFPValue, isGSCComplete });

</script>

<style scoped>
/* Your styles go here */
.form-container {
    display: flex;
    justify-content: space-between;
    gap: 20px; /* 这个可以调整间距 */
}

.form-group1,
.form-group2,
.form-group3 {
    width: 48%; /* 让每个组占据父容器的 48% 宽度 */
}

.el-form-item {
    margin-bottom: 15px; /* 可根据需要调整间距 */
}

.button-container {
    display: flex;
    justify-content: space-between;
    gap: 0px;
}

.gsc-form {
    max-width: 600px;
    margin: 0 auto;
}

.input-with-select {
    display: flex;
    align-items: center;
    margin-top: 13px;
}
</style>

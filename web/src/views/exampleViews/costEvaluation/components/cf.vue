<template>
  <el-form class="s-form" :model="dynamicValidateForm" label-width="auto" style="max-width: 600px">
    <el-row>
        <el-form-item label="选择规模变更调整因子">
            <el-radio-group
                v-model="dynamicValidateForm2.cf.value"
                @change="handleCFChange"
            >
                <el-radio value="项目立项" style="margin-right: 70px">项目立项</el-radio>
                <el-radio value="项目招标" style="margin-right: 70px">项目招标</el-radio>
                <el-radio value="项目早期" style="margin-right: 70px">项目早期</el-radio>
                <el-radio value="项目中期" style="margin-right: 70px">项目中期</el-radio>
                <el-radio value="项目完成" style="margin-right: 70px">项目完成</el-radio>
            </el-radio-group>
        </el-form-item>
    </el-row>

    <el-row style="margin-bottom: 15px; margin-top: 0">
        <el-descriptions
            class="margin-top"
            :column="5"
            size="28px"
            border
            label-width="80px"
        >
            <el-descriptions-item width="80px">
                <template #label>
                    <div class="cell-item"><el-tag size="medium">CF值</el-tag></div>
                </template>
                {{ dynamicValidateForm.cf.value }}
            </el-descriptions-item>
        </el-descriptions>
        <div class="button-container">
          <el-button type="primary" class="el-button el-button--primary" @click="fetchUFP">确定因子</el-button>
        </div>
    </el-row>



    <el-input v-model="s" style="max-width: 600px;height: 38px;" placeholder="点击计算DFP值">
        <template #prepend>
            <el-button type="primary" @click="calculateS">计算DFP值</el-button>
        </template>
    </el-input>

  </el-form>
</template>

<script lang="ts" setup>
import { reactive, ref, defineProps,  defineExpose, onMounted,watch } from 'vue'
import { ElMessage } from 'element-plus'
import axios from 'axios'

//定义props
const props = defineProps<{
  CfForm: {
    ufp: number;
    cf: CfItem;
  }
}>();

interface CfItem {
  project_id: string;
  s_id: number;
  type: string;
  value: number;
}
interface CfItem1 {
  project_id: string;
  s_id: number;
  type: string;
  value: string;
}

const dynamicValidateForm = reactive<{
  cf: CfItem;
}>({
  cf: {
    project_id: props.CfForm.cf.project_id,
    s_id: 0,
    type: 'cf',
    value: 0,
  },
})

const dynamicValidateForm2 = reactive<{
  cf: CfItem1;
}>({
  cf: {
    project_id: props.CfForm.cf.project_id,
    s_id: 0,
    type: 'cf',
    value: '',
  },
})


// 用于存储从后端获取的 UFP 值
const ufp = ref(0)

// 用于存储计算后的 S 值
const s = ref(0)

const radioData = [
  {
  label: '项目立项',
  value: 2.00,
  }, 
  {
    label: '项目招标',
    value: 1.50,
  }, 
  {
    label: '项目早期',
    value: 1.26,
  }, 
  {
    label: '项目中期',
    value: 1.26,
  }, 
  {
    label: '项目完成',
    value: 1.00,
  }]



const { CfForm } = props;

watch(CfForm.cf, (newVal, oldVal) => {
  fetchData();
})

const fetchData = async () => {
  
  const project_id = CfForm.cf.project_id;
  dynamicValidateForm.cf.project_id = project_id;
  console.log("dynamicValidateForm.cf: ", dynamicValidateForm.cf);
  console.log("props.CfForm.cf:" + props.CfForm.cf);
  console.log("project_idhhh: ", project_id);
  try {
    // 调用后端API获取数据
    const response = await axios.get(`http://localhost:9000/costEvaluation/getSItem`, {params: {project_id: project_id}});

    console.log("响应数据："+ response.data);
    console.log("cf值：" + response.data.cf[0].value);
    // 检查响应数据是否存在
    if (response.data.isOk) {
      let temp = response.data.cf[0].value;
      let cf_value = radioData.find(item=>item.value === temp)?.label as string
      dynamicValidateForm2.cf.value = cf_value;
      dynamicValidateForm.cf.value = temp;
    }
  } catch (error) {
    console.error('Error fetching S data:', error);
  }
}


// 从后端获取 UFP 值
const fetchUFP = async () => {
  try {
    const response = await axios.get(`http://localhost:9000/costEvaluation/ufp?project_id=1`)
    if (response.data.isOk) {
      ufp.value = parseFloat(response.data.ufp_num)
      console.log("UFP: ", ufp.value)
      ElMessage.success('因子获取成功')
    } else {
      ElMessage.error('后端返回失败: ' + response.data.msg)
    }
  } catch (error) {
    ElMessage.error('获取 UFP 失败: ' + error.message)
  }
}

const calculateS = () => {
  if (dynamicValidateForm.cf.value && CfForm.ufp) {
    s.value = dynamicValidateForm.cf.value * CfForm.ufp
    ElMessage.success('S 值计算成功')
  } else {
    ElMessage.warning('请选择下拉框选项并确保 UFP 值已获取')
  }
}

function handleCFChange(value) {
    if (value == '项目立项') {
      dynamicValidateForm.cf.value = 2.00;
    } else if (value == '项目招标') {
      dynamicValidateForm.cf.value = 1.50;
    } else if (value == '项目早期') {
      dynamicValidateForm.cf.value = 1.26;
    } else if (value == '项目中期') {
      dynamicValidateForm.cf.value = 1.26;
    } else if (value == '项目完成') {        
      dynamicValidateForm.cf.value = 1.00;
    }
}

const storeSValue = async () => {
  try {
    // 构建查询字符串
    const params = {
      project_id: dynamicValidateForm.cf.project_id,
      s_value: s.value,
    };

    // 发送GET请求
    const response = await axios.get(`http://localhost:9000/costEvaluation/storeS`, { params });

    console.log("s_value: ", s.value);
    console.log("project_id: ", dynamicValidateForm.cf.project_id);
    console.log("响应数据: ", response.data);

    if (response.data.isOk) {
      ElMessage.success('S 值存储成功');
    } else {
      ElMessage.error('后端返回失败: ' + response.data.msg);
    }
  } catch (error) {
    ElMessage.error('S 值存储失败: ' + error.message);
  }
};

const sendCfItemToBackend = async () => {
  try {
    // 构建请求体数据
    const data = {
      project_id: dynamicValidateForm.cf.project_id,
      type: dynamicValidateForm.cf.type,
      value: dynamicValidateForm.cf.value,
    };

    // 发送POST请求
    const response = await axios.post('http://localhost:9000/costEvaluation/saveCfItem', data);

    console.log("响应数据: ", response.data);

    if (response.data.isOk) {
      ElMessage.success('CF项存储成功');
    } else {
      ElMessage.error('后端返回失败: ' + response.data.msg);
    }
  } catch (error) {
    ElMessage.error('CF项存储失败: ' + error.message);
  }
};

defineExpose({  storeSValue,  sendCfItemToBackend });

</script>

<style scoped>
.form-container {
  display: flex;
  justify-content: space-between;
  gap: 20px; /* 这个可以调整间距 */
}

.form-group1, .form-group2 {
  display: flex;
  justify-content: space-between;
  gap: 20px; /* 这个可以调整间距 */
}

.el-form-item {
  margin-bottom: 15px; /* 可根据需要调整间距 */
}

.button-container {
  display: flex;
  justify-content: space-between;
  gap: 0px;
  margin-left: 13px;
  margin-top: 5px;
}

.s-form {
  max-width: 600px;
  margin: 0 auto;
  margin-top: 60px;
}

.input-with-select {
  display: flex;
  align-items: center;
  margin-top: 13px;
}
</style>
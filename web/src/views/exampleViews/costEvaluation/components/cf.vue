  <template>
        <el-form class="cf-form" :model="form" label-width="auto" style="max-width: 600px">
        <div class="form-container">
            <el-form-item label="规模变更调整因子选择">
            <el-select v-model="form.cf" placeholder="请根据项目阶段选择">
                <el-option label="项目立项" value=2.00 />
                <el-option label="项目招标" value=1.50 />
                <el-option label="项目早期" value=1.26 />
                <el-option label="项目中期" value=1.26 />
                <el-option label="项目完成" value=1.00 />
            </el-select>
            </el-form-item>
            <div class="button-container">
            <button type="primary" class="el-button el-button--primary" @click="onSubmit">确认</button>
            </div>
        </div>
        
        </el-form>
  </template>
  
  <script setup>
    // Your script logic goes here
    import { reactive } from 'vue'
    import { ElMessage } from 'element-plus'

    // do not use same name with ref
    const form = reactive({
      cf: null,
    })

    const onSubmit = () => {
        ElMessage.success('提交成功');
    }
  </script>
  
  <style scoped>
    /* Your styles go here */
    .form-container {
    display: flex;
    justify-content: center;
    gap: 20px;
  }

  .form-group1, .form-group2 {
    width: 48%; /* 让每个组占据父容器的 48% 宽度 */
  }
  
  .el-form-item {
    margin-bottom: 15px; /* 可根据需要调整间距 */
  }

  .button-container {
    display: flex;
    justify-content: center;
    gap: 0px
  }

  .cf-form {
    max-width: 600px;
    margin: 0 auto;
    margin-top: 50px;
  }
  </style>
  
  <template>
    <el-form class="gsc-form" :model="form" label-width="auto" style="max-width: 600px">
    <div class="form-container">
      <div class="form-group1">
        <!-- 1 -->
        <el-form-item label="数据通信">
          <el-select v-model="form.di1" placeholder="请选择DI值">
            <el-option label="0" value=0 />
            <el-option label="1" value=1 />
            <el-option label="2" value=2 />
            <el-option label="3" value=3 />
            <el-option label="4" value=4 />
            <el-option label="5" value=5 />
          </el-select>
        </el-form-item>
        <!-- 2 -->
        <el-form-item label="分布式数据处理">
          <el-select v-model="form.di2" placeholder="请选择DI值">
            <el-option label="0" value=0 />
            <el-option label="1" value=1 />
            <el-option label="2" value=2 />
            <el-option label="3" value=3 />
            <el-option label="4" value=4 />
            <el-option label="5" value=5 />
          </el-select>
        </el-form-item>
        <!-- 3 -->
        <el-form-item label="性能">
          <el-select v-model="form.di3" placeholder="请选择DI值">
            <el-option label="0" value=0 />
            <el-option label="1" value=1 />
            <el-option label="2" value=2 />
            <el-option label="3" value=3 />
            <el-option label="4" value=4 />
            <el-option label="5" value=5 />
          </el-select>
        </el-form-item>
        <!-- 4 -->
        <el-form-item label="重度配置">
          <el-select v-model="form.di4" placeholder="请选择DI值">
            <el-option label="0" value=0 />
            <el-option label="1" value=1 />
            <el-option label="2" value=2 />
            <el-option label="3" value=3 />
            <el-option label="4" value=4 />
            <el-option label="5" value=5 />
          </el-select>
        </el-form-item>
        <!-- 5 -->
        <el-form-item label="处理速率">
          <el-select v-model="form.di5" placeholder="请选择DI值">
            <el-option label="0" value=0 />
            <el-option label="1" value=1 />
            <el-option label="2" value=2 />
            <el-option label="3" value=3 />
            <el-option label="4" value=4 />
            <el-option label="5" value=5 />
          </el-select>
        </el-form-item>
        <!-- 6 -->
        <el-form-item label="在线数据输入">
          <el-select v-model="form.di6" placeholder="请选择DI值">
            <el-option label="0" value=0 />
            <el-option label="1" value=1 />
            <el-option label="2" value=2 />
            <el-option label="3" value=3 />
            <el-option label="4" value=4 />
            <el-option label="5" value=5 />
          </el-select>
        </el-form-item>
        <!-- 7 -->
        <el-form-item label="最终用户使用效率">
          <el-select v-model="form.di7" placeholder="请选择DI值">
            <el-option label="0" value=0 />
            <el-option label="1" value=1 />
            <el-option label="2" value=2 />
            <el-option label="3" value=3 />
            <el-option label="4" value=4 />
            <el-option label="5" value=5 />
          </el-select>
        </el-form-item>
      </div>

      <div class="form-group2">
        <!-- 8 -->
        <el-form-item label="在线升级">
          <el-select v-model="form.di8" placeholder="请选择DI值">
            <el-option label="0" value=0 />
            <el-option label="1" value=1 />
            <el-option label="2" value=2 />
            <el-option label="3" value=3 />
            <el-option label="4" value=4 />
            <el-option label="5" value=5 />
          </el-select>
        </el-form-item>
        <!-- 9 -->
        <el-form-item label="复杂处理">
          <el-select v-model="form.di9" placeholder="请选择DI值">
            <el-option label="0" value=0 />
            <el-option label="1" value=1 />
            <el-option label="2" value=2 />
            <el-option label="3" value=3 />
            <el-option label="4" value=4 />
            <el-option label="5" value=5 />
          </el-select>
        </el-form-item>
        <!-- 10 -->
        <el-form-item label="可重用性">
          <el-select v-model="form.di10" placeholder="请选择DI值">
            <el-option label="0" value=0 />
            <el-option label="1" value=1 />
            <el-option label="2" value=2 />
            <el-option label="3" value=3 />
            <el-option label="4" value=4 />
            <el-option label="5" value=5 />
          </el-select>
        </el-form-item>
        <!-- 11 -->
        <el-form-item label="易安装性">
          <el-select v-model="form.di11" placeholder="请选择DI值">
            <el-option label="0" value=0 />
            <el-option label="1" value=1 />
            <el-option label="2" value=2 />
            <el-option label="3" value=3 />
            <el-option label="4" value=4 />
            <el-option label="5" value=5 />
          </el-select>
        </el-form-item>
        <!-- 12 -->
        <el-form-item label="易操作性">
          <el-select v-model="form.di12" placeholder="请选择DI值">
            <el-option label="0" value=0 />
            <el-option label="1" value=1 />
            <el-option label="2" value=2 />
            <el-option label="3" value=3 />
            <el-option label="4" value=4 />
            <el-option label="5" value=5 />
          </el-select>
        </el-form-item>
        <!-- 13 -->
        <el-form-item label="多场所">
          <el-select v-model="form.di13" placeholder="请选择DI值">
            <el-option label="0" value=0 />
            <el-option label="1" value=1 />
            <el-option label="2" value=2 />
            <el-option label="3" value=3 />
            <el-option label="4" value=4 />
            <el-option label="5" value=5 />
          </el-select>
        </el-form-item>
        <!-- 14 -->
        <el-form-item label="支持变更">
          <el-select v-model="form.di14" placeholder="请选择DI值">
            <el-option label="0" value=0 />
            <el-option label="1" value=1 />
            <el-option label="2" value=2 />
            <el-option label="3" value=3 />
            <el-option label="4" value=4 />
            <el-option label="5" value=5 />
          </el-select>
        </el-form-item>
      </div>
    </div>
    <div class="calculateDFP-container">
    <el-input
      v-model="di.value"
      style="max-width: 600px"
      placeholder="点击计算总DI值"
      class="input-with-select"
    >
      <template #prepend>
        <el-button  type="primary" @click="onSubmit">计算总DI值</el-button>
      </template>
    </el-input>

    <el-input
      v-model="calculateDFP.value"
      style="max-width: 600px"
      placeholder="点击计算DFP值"
      class="input-with-select"
    >
      <template #prepend>
        <el-button  type="primary" @click="calculateDFP">计算DFP值</el-button>
      </template>
    </el-input>
  </div>
  </el-form>
  </template>
  
  <script setup>
    // Your script logic goes here
    import { reactive } from 'vue'

    // do not use same name with ref
    const form = reactive({
      di1: null,
      di2: null,
      di3: null,
      di4: null,
      di5: null,
      di6: null,
      di7: null,
      di8: null,
      di9: null,
      di10: null,
      di11: null,
      di12: null,
      di13: null,
      di14: null,
    })

    // 用于显示计算结果
    const di = reactive({
      value: ''
    });

    const calculateDFP = reactive({
      value: ''
    })

    const onSubmit = () => {
      // 获取所有 DI 值的和，防止 null 或 undefined 的情况
      const sum = [form.di1, form.di2, form.di3, form.di4, form.di5, form.di6, form.di7, form.di8, form.di9, form.di10, form.di11, form.di12, form.di13, form.di14]
        .map(Number) // 转换为数字，避免字符串加法
        .filter(val => !isNaN(val)) // 过滤掉无效值
        .reduce((acc, val) => acc + val, 0); // 计算和

      // 更新 input3 的值
      di.value = sum;
    }
  </script>
  
  <style scoped>
    /* Your styles go here */
  .form-container {
    display: flex;
    justify-content: space-between;
    gap: 20px; /* 这个可以调整间距 */
  }

  .form-group1, .form-group2 {
    width: 48%; /* 让每个组占据父容器的 48% 宽度 */
  }
  
  .el-form-item {
    margin-bottom: 15px; /* 可根据需要调整间距 */
  }

  .button-container {
    display: flex;
    justify-content: space-between;
    gap: 0px
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
  
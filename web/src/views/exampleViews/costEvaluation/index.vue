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
                    <div v-if="currentStep === 0" class="scroll-container">
                        <IFPUG />
                    </div>
                    <div v-if="currentStep === 1" class="scroll-container">
                        <GSC />
                    </div>
                    <div v-if="currentStep === 2" class="scroll-container">
                        <CF />
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

<script setup>
import { ref } from 'vue';
import GSC from './components/gsc.vue';
import CF from './components/cf.vue';
import IFPUG from './components/ifpug.vue';

const currentStep = ref(0);

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

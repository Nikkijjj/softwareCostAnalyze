<template>
    <div class="page-container main-view">
        <el-card class="box-card">
            <template #header>
                <el-steps class="steps-bar" :active="currentStep" finish-status="success">
                    <el-step title="Step 3" @click="currentStep = 0" />
                    <el-step title="GSC法" @click="currentStep = 1" />
                    <el-step title="规模变更调整因子法" @click="currentStep = 2" />
                </el-steps>
            </template>

            <!-- 显示不同步骤对应的内容 -->
            <div v-if="currentStep === 0">
                <p>Step 1 content here</p>
            </div>
            <div v-if="currentStep === 1">
                <GSC ref="GSC" />
            </div>
            <div v-if="currentStep === 2">
                <CF ref="CF" />
            </div>
            

            <el-button style="margin-top: 12px" @click="next">Next step</el-button>
        </el-card>
    </div>
</template>

<script>
import { defineComponent, ref } from 'vue';
import GSC from './components/gsc.vue';
import CF from './components/cf.vue';

export default defineComponent({
    components: {
        GSC,
        CF,
    },
    setup() {
        const currentStep = ref(0);

        const next = () => {
            if (currentStep.value < 2) { // 最多到第 2 步
                currentStep.value++;
            }
        };

        return {
            currentStep,
            next,
        };
    },
});
</script>

<style scoped lang="scss">
.box-card {
    margin-top: 20px;
    width: auto;
}

.steps-bar {
    width: auto;
}
</style>

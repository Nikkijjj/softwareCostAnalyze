<template>
    <el-form ref="formRef" :model="dynamicValidateForm" label-width="auto" class="demo-dynamic">
        <!--ILF ELF-->
        <el-row>
            <!--ILF-->
            <el-col :span="12" style="width: 450px">
                <el-form-item
                    v-for="(u, index) in dynamicValidateForm.ilfs"
                    :key="u.ufp_id"
                    :label="'ILF-' + (index + 1)"
                    :prop="'ilfs.' + index + '.detail'"
                    :rules="{
                        required: true,
                        message: '请输入ILF详细信息',
                        trigger: 'blur',
                    }"
                >
                    <!-- 调整布局 -->
                    <div style="display: flex; align-items: center">
                        <!-- 文本框 -->
                        <el-input
                            v-model="u.detail"
                            style="flex: 1; width: 300px; margin-right: 8px"
                            clearable
                            placeholder="请录入ILF详情"
                        />
                        <!-- 删除按钮 -->
                        <el-button
                            class="mt-2"
                            @click.prevent="removeUfps(u, u.type)"
                            type="danger"
                            :icon="Delete"
                            circle
                            plain
                        />
                        <!-- 添加按钮 -->
                        <el-button
                            class="mt-1"
                            @click.prevent="addUfps(u.type, index, 'ilfs.' + index + '.detail')"
                            type="success"
                            :icon="Plus"
                            circle
                            v-if="index == dynamicValidateForm.ilfs.length - 1"
                            plain
                        />
                    </div>
                </el-form-item>
            </el-col>
            <!--ELF-->
            <el-col :span="12" style="width: 450px">
                <el-form-item
                    v-for="(u, index) in dynamicValidateForm.elfs"
                    :key="u.ufp_id"
                    :label="'ELF-' + (index + 1)"
                    :prop="'elfs.' + index + '.detail'"
                    :rules="{
                        required: true,
                        message: '请输入ELF详细信息',
                        trigger: 'blur',
                    }"
                >
                    <!-- 调整布局 -->
                    <div style="display: flex; align-items: center">
                        <!-- 文本框 -->
                        <el-input
                            v-model="u.detail"
                            style="flex: 1; width: 300px; margin-right: 8px"
                            clearable
                            placeholder="请录入ELF详情"
                        />
                        <!-- 删除按钮 -->
                        <el-button
                            class="mt-2"
                            @click.prevent="removeUfps(u, u.type)"
                            type="danger"
                            :icon="Delete"
                            circle
                            plain
                        />
                        <!-- 添加按钮 -->
                        <el-button
                            class="mt-1"
                            @click.prevent="addUfps(u.type, index, 'elfs.' + index + '.detail')"
                            type="success"
                            :icon="Plus"
                            circle
                            v-if="index == dynamicValidateForm.elfs.length - 1"
                            plain
                        />
                    </div>
                </el-form-item>
            </el-col>
        </el-row>
        <el-divider style="margin-top: 0; margin-bottom: 15px"></el-divider>
        <!--EI EO-->
        <el-row>
            <!--EI-->
            <el-col :span="12" style="width: 450px">
                <el-form-item
                    v-for="(u, index) in dynamicValidateForm.eis"
                    :key="u.ufp_id"
                    :label="'EI-' + (index + 1)"
                    :prop="'eis.' + index + '.detail'"
                    :rules="{
                        required: true,
                        message: '请输入EI详细信息',
                        trigger: 'blur',
                    }"
                >
                    <!-- 调整布局 -->
                    <div style="display: flex; align-items: center">
                        <!-- 文本框 -->
                        <el-input
                            v-model="u.detail"
                            style="flex: 1; width: 300px; margin-right: 8px"
                            clearable
                            placeholder="请录入EI详情"
                        />
                        <!-- 删除按钮 -->
                        <el-button
                            class="mt-2"
                            @click.prevent="removeUfps(u, u.type)"
                            type="danger"
                            :icon="Delete"
                            circle
                            plain
                        />
                        <!-- 添加按钮 -->
                        <el-button
                            class="mt-1"
                            @click.prevent="addUfps(u.type, index, 'eis.' + index + '.detail')"
                            type="success"
                            :icon="Plus"
                            circle
                            v-if="index == dynamicValidateForm.eis.length - 1"
                            plain
                        />
                    </div>
                </el-form-item>
            </el-col>
            <!--EO-->
            <el-col :span="12" style="width: 450px">
                <el-form-item
                    v-for="(u, index) in dynamicValidateForm.eos"
                    :key="u.ufp_id"
                    :label="'EO-' + (index + 1)"
                    :prop="'eos.' + index + '.detail'"
                    :rules="{
                        required: true,
                        message: '请输入EO详细信息',
                        trigger: 'blur',
                    }"
                >
                    <!-- 调整布局 -->
                    <div style="display: flex; align-items: center">
                        <!-- 文本框 -->
                        <el-input
                            v-model="u.detail"
                            style="flex: 1; width: 300px; margin-right: 8px"
                            clearable
                            placeholder="请录入EO详情"
                        />
                        <!-- 删除按钮 -->
                        <el-button
                            class="mt-2"
                            @click.prevent="removeUfps(u, u.type)"
                            type="danger"
                            :icon="Delete"
                            circle
                            plain
                        />
                        <!-- 添加按钮 -->
                        <el-button
                            class="mt-1"
                            @click.prevent="addUfps(u.type, index, 'eos.' + index + '.detail')"
                            type="success"
                            :icon="Plus"
                            circle
                            v-if="index == dynamicValidateForm.eos.length - 1"
                            plain
                        />
                    </div>
                </el-form-item>
            </el-col>
        </el-row>
        <el-divider style="margin-top: 0; margin-bottom: 15px"></el-divider>
        <!--EQ-->
        <el-row>
            <el-row>
                <!--EQ-->
                <el-col :span="12" style="width: 450px">
                    <el-form-item
                        v-for="(u, index) in dynamicValidateForm.eqs"
                        :key="u.ufp_id"
                        :label="'EQ-' + (index + 1)"
                        :prop="'eqs.' + index + '.detail'"
                        :rules="{
                            required: true,
                            message: '请输入EQ详细信息',
                            trigger: 'blur',
                        }"
                    >
                        <!-- 调整布局 -->
                        <div style="display: flex; align-items: center">
                            <!-- 文本框 -->
                            <el-input
                                v-model="u.detail"
                                style="flex: 1; width: 300px; margin-right: 8px"
                                clearable
                                placeholder="请录入EQ详情"
                            />
                            <!-- 删除按钮 -->
                            <el-button
                                class="mt-2"
                                @click.prevent="removeUfps(u, u.type)"
                                type="danger"
                                :icon="Delete"
                                circle
                                plain
                            />
                            <!-- 添加按钮 -->
                            <el-button
                                class="mt-1"
                                @click.prevent="addUfps(u.type, index, 'eqs.' + index + '.detail')"
                                type="success"
                                :icon="Plus"
                                circle
                                v-if="index == dynamicValidateForm.eqs.length - 1"
                                plain
                            />
                        </div>
                    </el-form-item>
                </el-col>
            </el-row>
        </el-row>
        <el-divider style="margin-top: 0; margin-bottom: 10px"></el-divider>
        <!--Complexity-->
        <el-row>
            <el-form-item label="复杂度">
                <el-radio-group
                    v-model="dynamicValidateForm.complexity.detail"
                    @change="handleComplexityChange"
                >
                    <el-radio value="低" style="margin-right: 70px; margin-left: 30px">低</el-radio>
                    <el-radio value="中" style="margin-right: 70px">中</el-radio>
                    <el-radio value="高" style="margin-right: 70px">高</el-radio>
                </el-radio-group>
            </el-form-item>
        </el-row>
        <!--复杂度展示-->
        <el-row style="margin-bottom: 15px; margin-top: 0">
            <el-descriptions class="margin-top" :column="5" size="28px" border label-width="80px">
                <el-descriptions-item width="80px">
                    <template #label>
                        <div class="cell-item"><el-tag size="medium">ILF-UFP</el-tag></div>
                    </template>
                    {{ c_ilf }}
                </el-descriptions-item>
                <el-descriptions-item width="80px">
                    <template #label>
                        <div class="cell-item"><el-tag size="medium">ELF-UFP</el-tag></div>
                    </template>
                    {{ c_elf }}
                </el-descriptions-item>
                <el-descriptions-item width="80px">
                    <template #label>
                        <div class="cell-item"><el-tag size="medium">EI-UFP</el-tag></div>
                    </template>
                    {{ c_ei }}
                </el-descriptions-item>
                <el-descriptions-item width="80px">
                    <template #label>
                        <div class="cell-item"><el-tag size="medium">EO-UFP</el-tag></div>
                    </template>
                    {{ c_eo }}
                </el-descriptions-item>
                <el-descriptions-item width="80px">
                    <template #label>
                        <div class="cell-item"><el-tag size="medium">EQ_UFP</el-tag></div>
                    </template>
                    {{ c_eq }}
                </el-descriptions-item>
            </el-descriptions>
        </el-row>
        <el-divider style="margin-top: 0; margin-bottom: 15px"></el-divider>
        <!--计算UFP-->
        <el-input
            v-model="dynamicValidateForm.ufp"
            style="max-width: 600px; height: 38px"
            placeholder="点击计算UFP值"
        >
            <template #prepend>
                <el-button type="primary" @click.prevent="computeUFP">计算UFP值</el-button>
            </template>
        </el-input>
        <el-alert type="info" show-icon :closable="false" style="margin-top:5px;">
            <p>
                计算公式：UFP=ILF*A+EIF*B+EI*C+EO*D+EQ*E
            </p>
        </el-alert>
    </el-form>
</template>

<script lang="ts" setup>
import { reactive, ref, watch, defineProps, defineEmits, watchEffect } from 'vue';
import type { FormInstance } from 'element-plus';
import { Plus, Delete, StarFilled } from '@element-plus/icons-vue';

let c_ilf = ref<number>(0);
let c_elf = ref<number>(0);
let c_ei = ref<number>(0);
let c_eo = ref<number>(0);
let c_eq = ref<number>(0);
const formRef = ref<FormInstance>();

const props = defineProps({
    modelValue: {
        type: Object,
        required: true,
    },
});

const emit = defineEmits(['update:modelValue']);

// 本地拷贝，防止直接修改父组件数据
const dynamicValidateForm = reactive({ ...props.modelValue });
const resetForm = (formEl: FormInstance | undefined) => {
    if (!formEl) return;
    formEl.resetFields();
};
// 子组件：监听并同步父组件传递的数据
watch(
    () => props.modelValue, // 监听父组件数据
    (newValue) => {
        // resetForm(formRef.value)
        c_ilf.value = 0;
        c_elf.value = 0;
        c_ei.value = 0;
        c_eo.value = 0;
        c_eq.value = 0;
        Object.assign(dynamicValidateForm, newValue); // 浅拷贝父组件数据到本地数据
        handleComplexityChange(dynamicValidateForm.complexity.detail);
        console.log('父组件数据更新:', newValue);
    },
    { deep: true, immediate: true }, // 深度监听并在初始化时执行
);

// 子组件：监控本地数据变化并发射事件同步到父组件
watch(
    dynamicValidateForm, // 监听本地数据
    (newValue) => {
        emit('update:modelValue', { ...newValue }); // 将新的值发射到父组件
        console.log('同步到父组件:', newValue);
    },
    { deep: true },
);

//移除功能点
const removeUfps = (item, type: string) => {
    let index;
    console.log(type);
    switch (type) {
        case 'ilf':
            index = dynamicValidateForm.ilfs.indexOf(item);
            if (index !== -1) {
                dynamicValidateForm.ilfs = [
                    ...dynamicValidateForm.ilfs.slice(0, index),
                    ...dynamicValidateForm.ilfs.slice(index + 1),
                ];
            }
            break;
        case 'elf':
            index = dynamicValidateForm.elfs.indexOf(item);
            if (index !== -1) {
                dynamicValidateForm.elfs = [
                    ...dynamicValidateForm.elfs.slice(0, index),
                    ...dynamicValidateForm.elfs.slice(index + 1),
                ];
            }
            break;
        case 'ei':
            index = dynamicValidateForm.eis.indexOf(item);
            if (index !== -1) {
                dynamicValidateForm.eis = [
                    ...dynamicValidateForm.eis.slice(0, index),
                    ...dynamicValidateForm.eis.slice(index + 1),
                ];
            }
            break;
        case 'eo':
            index = dynamicValidateForm.eos.indexOf(item);
            if (index !== -1) {
                dynamicValidateForm.eos = [
                    ...dynamicValidateForm.eos.slice(0, index),
                    ...dynamicValidateForm.eos.slice(index + 1),
                ];
            }
            break;
        case 'eq':
            index = dynamicValidateForm.eqs.indexOf(item);
            if (index !== -1) {
                dynamicValidateForm.eqs = [
                    ...dynamicValidateForm.eqs.slice(0, index),
                    ...dynamicValidateForm.eqs.slice(index + 1),
                ];
            }
            break;
        default:
            console.log('删除：没有传递任何一项ufp参数');
            break;
    }
};
//增加功能点
const addUfps = (type: string, index: number, prop: string) => {
    formRef.value.validateField(prop, (valid: boolean) => {
        if (valid) {
            // 校验通过，新增输入框
            const newItem = {
                module_id: '',
                ufp_id: Date.now() % 2147483647,
                type,
                detail: '',
            };
            if (type === 'ilf') dynamicValidateForm.ilfs.push(newItem);
            if (type === 'elf') dynamicValidateForm.elfs.push(newItem);
            if (type === 'ei') dynamicValidateForm.eis.push(newItem);
            if (type === 'eo') dynamicValidateForm.eos.push(newItem);
            if (type === 'eq') dynamicValidateForm.eqs.push(newItem);
        }
    });
};
//选择复杂度
function handleComplexityChange(value: string) {
    if (value == '低') {
        c_ilf.value = 7;
        c_elf.value = 5;
        c_ei.value = 3;
        c_eo.value = 4;
        c_eq.value = 3;
    } else if (value == '中') {
        c_ilf.value = 10;
        c_elf.value = 7;
        c_ei.value = 4;
        c_eo.value = 5;
        c_eq.value = 4;
    } else if (value == '高') {
        c_ilf.value = 15;
        c_elf.value = 10;
        c_ei.value = 6;
        c_eo.value = 7;
        c_eq.value = 6;
    }
    computeUFP();
}

//计算UFP
async function computeUFP() {
    const a = dynamicValidateForm.ilfs.filter((item) => item.detail).length;
    const b = dynamicValidateForm.elfs.filter((item) => item.detail).length;
    const c = dynamicValidateForm.eis.filter((item) => item.detail).length;
    const d = dynamicValidateForm.eos.filter((item) => item.detail).length;
    const e = dynamicValidateForm.eqs.filter((item) => item.detail).length;

    dynamicValidateForm.ufp =
        a * c_ilf.value + b * c_elf.value + c * c_ei.value + d * c_eo.value + e * c_eq.value;

    console.log('子组件中ufp：' + dynamicValidateForm.ufp);
}
</script>

<style lang="scss"></style>

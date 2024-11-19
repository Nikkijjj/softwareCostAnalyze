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
                                    @click.prevent="
                                        addUfps(u.type, index, 'eqs.' + index + '.detail')
                                    "
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
                        <el-radio value="低" style="margin-right: 70px; margin-left: 30px"
                            >低</el-radio
                        >
                        <el-radio value="中" style="margin-right: 70px">中</el-radio>
                        <el-radio value="高" style="margin-right: 70px">高</el-radio>
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
            <el-input v-model="ufp" style="max-width: 600px;height: 38px;" placeholder="点击计算DFP值">
                <template #prepend>
                    <el-button type="primary" @click="">计算DFP值</el-button>
                </template>
            </el-input>
        </el-form>
</template>

<script lang="ts" setup>
import { reactive, ref } from 'vue';
import type { FormInstance } from 'element-plus';
import { Plus, Delete, StarFilled } from '@element-plus/icons-vue';

let c_ilf = ref<number>();
let c_elf = ref<number>();
let c_ei = ref<number>();
let c_eo = ref<number>();
let c_eq = ref<number>();
let ufp = ref<number>();
const formRef = ref<FormInstance>();
const dynamicValidateForm = reactive<{
    ilfs: UfpItem[];
    elfs: UfpItem[];
    eis: UfpItem[];
    eos: UfpItem[];
    eqs: UfpItem[];
    complexity: UfpItem;
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
});

interface UfpItem {
    module_id: string;
    ufp_id: number;
    type: string;
    detail: string;
}

const removeUfps = (item: UfpItem, type: string) => {
    let index;
    console.log(type);
    switch (type) {
        case 'ilf':
            index = dynamicValidateForm.ilfs.indexOf(item);
            if (index !== -1) {
                dynamicValidateForm.ilfs.splice(index, 1);
            }
            break;
        case 'elf':
            index = dynamicValidateForm.elfs.indexOf(item);
            if (index !== -1) {
                dynamicValidateForm.elfs.splice(index, 1);
            }
            break;
        case 'ei':
            index = dynamicValidateForm.eis.indexOf(item);
            if (index !== -1) {
                dynamicValidateForm.eis.splice(index, 1);
            }
            break;
        case 'eo':
            index = dynamicValidateForm.eos.indexOf(item);
            if (index !== -1) {
                dynamicValidateForm.eos.splice(index, 1);
            }
            break;
        case 'eq':
            index = dynamicValidateForm.eqs.indexOf(item);
            if (index !== -1) {
                dynamicValidateForm.eqs.splice(index, 1);
            }
            break;
        default:
            console.log('删除：没有传递任何一项ufp参数');
            break;
    }
};

const addUfps = (type: string, index: number, prop: string) => {
    formRef.value.validateField(prop, (valid: boolean) => {
        if (valid) {
            // 校验通过，新增输入框
            const newItem = {
                module_id: '',
                ufp_id: Date.now(),
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

const submitForm = (formEl: FormInstance | undefined) => {
    if (!formEl) return;
    formEl.validate((valid) => {
        if (valid) {
            console.log('submit!');
        } else {
            console.log('error submit!');
        }
    });
};

const resetForm = (formEl: FormInstance | undefined) => {
    if (!formEl) return;
    formEl.resetFields();
};

function handleComplexityChange(value) {
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
}
</script>

<style lang="scss">

</style>

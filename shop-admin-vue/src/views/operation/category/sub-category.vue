<template>
    <el-dialog :title="title" v-model="open" append-to-body>
        <div class="app-container">
            <el-row :gutter="10" class="mb8">
                <el-col :span="1.5">
                    <el-button
                        type="primary"
                        plain
                        icon="Plus"
                        @click="handleAdd"
                    >新增</el-button>
                </el-col>
            </el-row>

            <el-table v-loading="loading" :data="categoryList">
                <el-table-column label="种类编号" align="center" prop="categoryId" />
                <el-table-column label="种类编码" align="center" prop="categoryCode" />
                <el-table-column label="种类名称" align="center" prop="categoryName" />
                <el-table-column label="创建时间" align="center" prop="createTime" width="180">
                    <template #default="scope">
                        <span>{{ parseTime(scope.row.createTime) }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="180" align="center" class-name="small-padding fixed-width">
                    <template #default="scope">
                        <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)">修改</el-button>
                        <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>

            <pagination
                v-show="total > 0"
                :total="total"
                v-model:page="queryParams.pageNum"
                v-model:limit="queryParams.pageSize"
                @pagination="getList"
            />

            <!-- 添加或修改种类对话框 -->
            <el-dialog :title="editTitle" v-model="editOpen" width="500px" append-to-body>
                <el-form ref="categoryRef" :model="form" :rules="rules" label-width="80px">
                    <el-form-item label="种类名称" prop="categoryName">
                        <el-input v-model="form.categoryName" placeholder="请输入种类名称" />
                    </el-form-item>
                    <el-form-item label="种类编码" prop="categoryCode">
                        <el-input v-model="form.categoryCode" placeholder="请输入编码名称" />
                    </el-form-item>
                    <el-form-item label="备注" prop="remark">
                        <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
                    </el-form-item>
                </el-form>
                <template #footer>
                    <div class="dialog-footer">
                        <el-button type="primary" @click="submitForm">确 定</el-button>
                        <el-button @click="cancel">取 消</el-button>
                    </div>
                </template>
            </el-dialog>
        </div>
        <template #footer>
            <div class="dialog-footer">
                <el-button @click="open = false">取 消</el-button>
            </div>
        </template>
    </el-dialog>
</template>

<script setup name="SubCategory">
import { listCategory, addCategory, delCategory, getCategory, updateCategory } from "@/api/operation/category";

const { proxy } = getCurrentInstance();

const categoryList = ref([]);
const editOpen = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const editTitle = ref("")

const props = defineProps({
    data: {
        required: true,
        type: Object
    },
    open: {
        required: true,
        type: Boolean
    },
    close: {
        type: Function,
        default: () => { }
    }
})

const data = reactive({
    form: {},
    queryParams: {
        pageNum: 1,
        pageSize: 10
    },
    rules: {
        categoryName: [{ required: true, message: "种类名称不能为空", trigger: "blur" }],
        categoryCode: [{ required: true, message: "种类编码不能为空", trigger: "blur" }],
    }
});
const { queryParams, form, rules } = toRefs(data);

watch(() => props.open, (newVal) => {
    if (newVal) {
        getList()
    }
});

const title = computed(() => `${props.data.categoryName} - 二级分类`);

const emit = defineEmits();
const open = computed({
    get() {
        return props.open
    },
    set(value) {
        emit('update:open', value)
    }
})

/** 查询种类列表 */
function getList() {
    loading.value = true;
    listCategory({ ...queryParams.value, pid: props.data.categoryId }).then(response => {
        categoryList.value = response.rows;
        total.value = response.total;
        loading.value = false;
    });
}

function reset() {
    form.value = {
        categoryId: undefined,
        categoryCode: undefined,
        categoryName: undefined,
        remark: undefined
    };
    proxy.resetForm("categoryRef");
}

/** 新增按钮操作 */
function handleAdd() {
    reset();
    editOpen.value = true;
    editTitle.value = `添加${title.value}`;
}

/** 修改按钮操作 */
function handleUpdate(row) {
    reset();
    const categoryId = row.categoryId || ids.value;
    getCategory(categoryId).then(response => {
        form.value = response.data;
        editOpen.value = true;
        editTitle.value = `修改${title.value}`;
    });
}
/** 删除按钮操作 */
function handleDelete(row) {
    const categoryIds = row.categoryId || ids.value;
    proxy.$modal.confirm('是否确认删除种类编号为"' + categoryIds + '"的数据项？').then(function() {
        return delCategory(categoryIds);
    }).then(() => {
        getList();
        proxy.$modal.msgSuccess("删除成功");
    }).catch(() => {});
}

/** 取消按钮 */
function cancel() {
    editOpen.value = false;
    reset();
}

/** 提交按钮 */
function submitForm() {
    proxy.$refs["categoryRef"].validate(valid => {
        if (valid) {
            if (form.value.categoryId != undefined) {
                updateCategory({
                    ...form.value,
                    pid: props.data.categoryId
                }).then(response => {
                    proxy.$modal.msgSuccess("修改成功");
                    editOpen.value = false;
                    getList();
                });
            } else {
                addCategory({
                    ...form.value,
                    pid: props.data.categoryId
                }).then(response => {
                    proxy.$modal.msgSuccess("新增成功");
                    editOpen.value = false;
                    getList();
                });
            }
        }
    });
}
</script>

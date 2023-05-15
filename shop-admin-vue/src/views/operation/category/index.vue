<template>
   <div class="app-container">
      <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch">
         <el-form-item label="种类编码" prop="categoryCode">
            <el-input
               v-model="queryParams.categoryCode"
               placeholder="请输入种类编码"
               clearable
               style="width: 200px"
               @keyup.enter="handleQuery"
            />
         </el-form-item>
         <el-form-item label="种类名称" prop="categoryName">
            <el-input
               v-model="queryParams.categoryName"
               placeholder="请输入种类名称"
               clearable
               style="width: 200px"
               @keyup.enter="handleQuery"
            />
         </el-form-item>
         <el-form-item>
            <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
            <el-button icon="Refresh" @click="resetQuery">重置</el-button>
         </el-form-item>
      </el-form>

      <el-row :gutter="10" class="mb8">
         <el-col :span="1.5">
            <el-button
               type="primary"
               plain
               icon="Plus"
               @click="handleAdd"
            >新增</el-button>
         </el-col>
         <el-col :span="1.5">
            <el-button
               type="success"
               plain
               icon="Edit"
               :disabled="single"
               @click="handleUpdate"
            >修改</el-button>
         </el-col>
         <el-col :span="1.5">
            <el-button
               type="danger"
               plain
               icon="Delete"
               :disabled="multiple"
               @click="handleDelete"
            >删除</el-button>
         </el-col>
         <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>

      <el-table v-loading="loading" :data="categoryList" @selection-change="handleSelectionChange">
         <el-table-column type="selection" width="55" align="center" />
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
               <el-button link type="primary" icon="Edit" @click="handleViewSub(scope.row)">查看二级分类</el-button>
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
      <el-dialog :title="title" v-model="open" width="500px" append-to-body>
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
      <sub-category :data="currentRow" v-model:open="openSub"></sub-category>
   </div>
</template>

<script setup name="Category">
import { listCategory, addCategory, delCategory, getCategory, updateCategory } from "@/api/operation/category";
import SubCategory from "@/views/operation/category/sub-category.vue";

const { proxy } = getCurrentInstance();
const { sys_normal_disable } = proxy.useDict("sys_normal_disable");

const categoryList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const currentRow = ref({});
const openSub = ref(false);

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    categoryCode: undefined,
    categoryName: undefined,
  },
  rules: {
    categoryName: [{ required: true, message: "种类名称不能为空", trigger: "blur" }],
    categoryCode: [{ required: true, message: "种类编码不能为空", trigger: "blur" }],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询种类列表 */
function getList() {
  loading.value = true;
  listCategory(queryParams.value).then(response => {
    categoryList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}
/** 取消按钮 */
function cancel() {
  open.value = false;
  reset();
}
/** 表单重置 */
function reset() {
  form.value = {
    categoryId: undefined,
    categoryCode: undefined,
    categoryName: undefined,
    remark: undefined
  };
  proxy.resetForm("categoryRef");
}
/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}
/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}
/** 多选框选中数据 */
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.categoryId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}
/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加种类";
}
/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const categoryId = row.categoryId || ids.value;
  getCategory(categoryId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改种类";
  });
}

/** 查看二级分类列表 */
function handleViewSub(row) {
   currentRow.value = row
   openSub.value = true
}
/** 提交按钮 */
function submitForm() {
  proxy.$refs["categoryRef"].validate(valid => {
    if (valid) {
      if (form.value.categoryId != undefined) {
        updateCategory(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addCategory(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
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

getList();
</script>

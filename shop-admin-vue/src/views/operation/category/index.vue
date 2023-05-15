<script setup name="Category" lang="ts">
import type { ComponentInternalInstance } from 'vue'
import { getCurrentInstance, reactive, ref, toRefs } from 'vue'
import { addCategory, delCategory, getCategory, listCategory, updateCategory } from '@/api/operation/category'
import { parseTime } from '@/utils/helpers'
import SubCategory from '@/views/operation/category/sub-category.vue'

const { proxy } = getCurrentInstance() as ComponentInternalInstance

const categoryList = ref<any[]>([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref<number[]>([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref('')
const currentRow = ref({})
const openSub = ref(false)

const data = reactive<{
  form: any
  queryParams: any
  rules: any
}>({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    categoryCode: undefined,
    categoryName: undefined,
  },
  rules: {
    categoryName: [{ required: true, message: '种类名称不能为空', trigger: 'blur' }],
    categoryCode: [{ required: true, message: '种类编码不能为空', trigger: 'blur' }],
  },
})

const { queryParams, form, rules } = toRefs(data)

/** 查询种类列表 */
function getList() {
  loading.value = true
  listCategory(queryParams.value).then((response: any) => {
    categoryList.value = response.rows
    total.value = response.total
    loading.value = false
  })
}
/** 取消按钮 */
function cancel() {
  open.value = false
  reset()
}
/** 表单重置 */
function reset() {
  form.value = {
    categoryId: undefined,
    categoryCode: undefined,
    categoryName: undefined,
    remark: undefined,
  }
  proxy!.resetForm('categoryRef')
}
/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1
  getList()
}
/** 重置按钮操作 */
function resetQuery() {
  proxy!.resetForm('queryRef')
  handleQuery()
}
/** 多选框选中数据 */
function handleSelectionChange(selection: any[]) {
  ids.value = selection.map(item => item.categoryId)
  single.value = selection.length !== 1
  multiple.value = !selection.length
}
/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = '添加种类'
}
/** 修改按钮操作 */
function handleUpdate(row: any) {
  reset()
  const categoryId = row.categoryId || ids.value
  getCategory(categoryId).then((response) => {
    form.value = response.data
    open.value = true
    title.value = '修改种类'
  })
}

/** 查看二级分类列表 */
function handleViewSub(row: any) {
  currentRow.value = row
  openSub.value = true
}

/** 提交按钮 */
function submitForm() {
  (proxy?.$refs.categoryRef as any).validate((valid: any) => {
    if (valid) {
      if (form.value.categoryId !== undefined) {
        updateCategory(form.value).then(() => {
          proxy!.$modal.msgSuccess('修改成功')
          open.value = false
          getList()
        })
      }
      else {
        addCategory(form.value).then(() => {
          proxy!.$modal.msgSuccess('新增成功')
          open.value = false
          getList()
        })
      }
    }
  })
}
/** 删除按钮操作 */
function handleDelete(row: any) {
  const categoryIds = row.categoryId || ids.value
  proxy!.$modal
    .confirm(`是否确认删除种类编号为"${categoryIds}"的数据项？`)
    .then(() => {
      return delCategory(categoryIds)
    })
    .then(() => {
      getList()
      proxy!.$modal.msgSuccess('删除成功')
    })
}
getList()
</script>

<template>
  <div class="app-container">
    <el-form v-show="showSearch" ref="queryRef" :model="queryParams" :inline="true">
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
        <el-button type="primary" icon="Search" @click="handleQuery">
          搜索
        </el-button>
        <el-button icon="Refresh" @click="resetQuery">
          重置
        </el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
        >
          新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
        >
          修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
        >
          删除
        </el-button>
      </el-col>
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
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleViewSub(scope.row)">
            查看二级分类
          </el-button>
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)">
            修改
          </el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      :total="total"
      @pagination="getList"
    />

    <!-- 添加或修改种类对话框 -->
    <el-dialog v-model="open" :title="title" width="500px" append-to-body>
      <el-form ref="categoryRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="种类名称" prop="categoryName">
          <el-input v-model="form.categoryName" placeholder="请输入种类名称" />
        </el-form-item>
        <el-form-item label="种类编码" prop="categoryCode">
          <el-input v-model="form.categoryCode" placeholder="请输入种类编码" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">
            确 定
          </el-button>
          <el-button @click="cancel">
            取 消
          </el-button>
        </div>
      </template>
    </el-dialog>
    <SubCategory v-model:open="openSub" :data="currentRow" />
  </div>
</template>

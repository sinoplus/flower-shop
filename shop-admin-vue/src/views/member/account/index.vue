<script setup name="Account" lang="ts">
import type { ComponentInternalInstance } from 'vue'
import { getCurrentInstance, reactive, ref, toRefs } from 'vue'
import { addRecord, deleteRecord, getRecord, queryList, updateRecord } from '@/api/member/account'
import { parseTime } from '@/utils/helpers'

const { proxy } = getCurrentInstance() as ComponentInternalInstance

const { sys_user_sex } = proxy!.useDict('sys_user_sex')

const tableList = ref<any[]>([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref<number[]>([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref('')

const data = reactive<{
  form: any
  queryParams: any
  rules: any
}>({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    accountName: undefined,
    phoneNumber: undefined,
    realName: undefined,
  },
  rules: {
    accountName: [{ required: true, message: '用户名不能为空', trigger: 'blur' }],
    phoneNumber: [{ required: true, message: '手机号不能为空', trigger: 'blur' }],
  },
})

const { form, queryParams, rules } = toRefs(data)

/** 查询公告列表 */
function getList() {
  loading.value = true
  queryList(queryParams.value).then((response: any) => {
    tableList.value = response.rows
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
    accountId: undefined,
    accountName: undefined,
    phoneNumber: undefined,
    realName: undefined,
    sex: undefined,
    email: undefined,
    address: undefined,
  }
  proxy!.resetForm('formRef')
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
  ids.value = selection.map(item => item.accountId)
  single.value = selection.length !== 1
  multiple.value = !selection.length
}
/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = '添加公告'
}
/** 修改按钮操作 */
function handleUpdate(row: any) {
  reset()
  const accountId = row.accountId || ids.value
  getRecord(accountId).then((response) => {
    form.value = response.data
    open.value = true
    title.value = '修改公告'
  })
}
/** 提交按钮 */
function submitForm() {
  (proxy?.$refs.formRef as any).validate((valid: any) => {
    if (valid) {
      if (form.value.accountId !== undefined) {
        updateRecord(form.value).then(() => {
          proxy!.$modal.msgSuccess('修改成功')
          open.value = false
          getList()
        })
      }
      else {
        addRecord(form.value).then(() => {
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
  const recordIds = row.accountId || ids.value
  proxy!.$modal
    .confirm(`是否确认删除公告编号为"${recordIds}"的数据项？`)
    .then(() => {
      return deleteRecord(recordIds)
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
      <el-form-item label="用户名" prop="accountName">
        <el-input
          v-model="queryParams.accountName"
          placeholder="请输入用户名"
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
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
        >
          删除
        </el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @query-table="getList" />
    </el-row>

    <el-table v-loading="loading" :data="tableList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="accountId" width="100" />
      <el-table-column
        label="用户名"
        align="center"
        prop="accountName"
      />
      <el-table-column
        label="手机号"
        align="center"
        prop="phoneNumber"
      />
      <el-table-column
        label="真实姓名"
        align="center"
        prop="realName"
      />
      <el-table-column label="性别" align="center" prop="sex" width="100">
        <template #default="scope">
          <dict-tag :options="sys_user_sex" :value="scope.row.sex" />
        </template>
      </el-table-column>
      <el-table-column
        label="邮箱"
        align="center"
        prop="email"
      />
      <el-table-column
        label="地址"
        align="center"
        prop="address"
      />
      <el-table-column label="创建者" align="center" prop="createBy" width="100" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="100">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button
            link
            type="primary"
            icon="Edit"
            @click="handleUpdate(scope.row)"
          >
            修改
          </el-button>
          <el-button
            link
            type="primary"
            icon="Delete"
            @click="handleDelete(scope.row)"
          >
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

    <!-- 添加或修改公告对话框 -->
    <el-dialog v-model="open" :title="title" width="780px" append-to-body>
      <el-form ref="formRef" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="用户名" prop="accountName">
              <el-input v-model="form.accountName" placeholder="请输入公告标题" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="手机号" prop="phoneNumber">
              <el-input v-model="form.phoneNumber" placeholder="请输入手机号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="真实姓名" prop="phoneNumber">
              <el-input v-model="form.realName" placeholder="请输入真实姓名" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="性别" prop="sex">
              <el-select v-model="form.sex" placeholder="请选择">
                <el-option
                  v-for="dict in sys_user_sex"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="form.email" placeholder="请输入邮箱" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="地址" prop="address">
              <el-input v-model="form.address" placeholder="请输入地址" />
            </el-form-item>
          </el-col>
        </el-row>
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
  </div>
</template>

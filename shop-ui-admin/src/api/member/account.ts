import request from '@/utils/request'

// 查询列表
export function queryList(query: any) {
  return request({
    url: '/member/account/list',
    method: 'get',
    params: query,
  })
}

// 查询详细
export function getRecord(id: any) {
  return request({
    url: `/member/account/${id}`,
    method: 'get',
  })
}

// 新增
export function addRecord(data: any) {
  return request({
    url: '/member/account',
    method: 'post',
    data,
  })
}

// 修改
export function updateRecord(data: any) {
  return request({
    url: '/member/account',
    method: 'put',
    data,
  })
}

// 删除
export function deleteRecord(id: any) {
  return request({
    url: `/member/account/${id}`,
    method: 'delete',
  })
}

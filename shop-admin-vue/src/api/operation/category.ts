import request from '@/utils/request'

// 查询种类列表
export function listCategory(query: any) {
  return request({
    url: '/operation/category/list',
    method: 'get',
    params: query,
  })
}

// 查询种类详细
export function getCategory(categoryId: string) {
  return request({
    url: `/operation/category/${categoryId}`,
    method: 'get',
  })
}

// 新增种类
export function addCategory(data: any) {
  return request({
    url: '/operation/category',
    method: 'post',
    data,
  })
}

// 修改种类
export function updateCategory(data: any) {
  return request({
    url: '/operation/category',
    method: 'put',
    data,
  })
}

// 删除种类
export function delCategory(categoryId: any) {
  return request({
    url: `/operation/category/${categoryId}`,
    method: 'delete',
  })
}

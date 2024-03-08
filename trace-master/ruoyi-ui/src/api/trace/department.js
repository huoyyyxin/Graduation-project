import request from '@/utils/request'

// 查询department列表
export function listDepartment(query) {
  return request({
    url: '/trace/department/list',
    method: 'get',
    params: query
  })
}

// 查询department详细
export function getDepartment(departmentId) {
  return request({
    url: '/trace/department/' + departmentId,
    method: 'get'
  })
}

// 新增department
export function addDepartment(data) {
  return request({
    url: '/trace/department',
    method: 'post',
    data: data
  })
}

// 修改department
export function updateDepartment(data) {
  return request({
    url: '/trace/department',
    method: 'put',
    data: data
  })
}

// 删除department
export function delDepartment(departmentId) {
  return request({
    url: '/trace/department/' + departmentId,
    method: 'delete'
  })
}

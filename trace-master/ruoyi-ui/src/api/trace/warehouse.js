import request from '@/utils/request'

// 查询warehouse列表
export function listWarehouse(query) {
  return request({
    url: '/trace/warehouse/list',
    method: 'get',
    params: query
  })
}

// 查询warehouse详细
export function getWarehouse(id) {
  return request({
    url: '/trace/warehouse/' + id,
    method: 'get'
  })
}

// 新增warehouse
export function addWarehouse(data) {
  return request({
    url: '/trace/warehouse',
    method: 'post',
    data: data
  })
}

// 修改warehouse
export function updateWarehouse(data) {
  return request({
    url: '/trace/warehouse',
    method: 'put',
    data: data
  })
}

// 删除warehouse
export function delWarehouse(id) {
  return request({
    url: '/trace/warehouse/' + id,
    method: 'delete'
  })
}

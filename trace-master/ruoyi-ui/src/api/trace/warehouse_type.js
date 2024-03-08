import request from '@/utils/request'

// 查询warehouse_type列表
export function listWarehouse_type(query) {
  return request({
    url: '/trace/warehouse_type/list',
    method: 'get',
    params: query
  })
}

// 查询warehouse_type详细
export function getWarehouse_type(warehouseTypeId) {
  return request({
    url: '/trace/warehouse_type/' + warehouseTypeId,
    method: 'get'
  })
}

// 新增warehouse_type
export function addWarehouse_type(data) {
  return request({
    url: '/trace/warehouse_type',
    method: 'post',
    data: data
  })
}

// 修改warehouse_type
export function updateWarehouse_type(data) {
  return request({
    url: '/trace/warehouse_type',
    method: 'put',
    data: data
  })
}

// 删除warehouse_type
export function delWarehouse_type(warehouseTypeId) {
  return request({
    url: '/trace/warehouse_type/' + warehouseTypeId,
    method: 'delete'
  })
}

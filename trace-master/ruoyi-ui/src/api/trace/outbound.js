import request from '@/utils/request'

// 查询销售出库列表
export function listOutbound(query) {
  return request({
    url: '/trace/outbound/list',
    method: 'get',
    params: query
  })
}

// 查询销售出库详细
export function getOutbound(id) {
  return request({
    url: '/trace/outbound/' + id,
    method: 'get'
  })
}

// 新增销售出库
export function addOutbound(data) {
  return request({
    url: '/trace/outbound',
    method: 'post',
    data: data
  })
}

// 修改销售出库
export function updateOutbound(data) {
  return request({
    url: '/trace/outbound',
    method: 'put',
    data: data
  })
}

// 删除销售出库
export function delOutbound(id) {
  return request({
    url: '/trace/outbound/' + id,
    method: 'delete'
  })
}

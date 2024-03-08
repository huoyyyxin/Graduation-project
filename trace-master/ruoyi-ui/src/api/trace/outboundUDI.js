import request from '@/utils/request'

// 查询销售出库UDI信息列表
export function listOutboundUDI(query) {
  return request({
    url: '/trace/outboundUDI/list',
    method: 'get',
    params: query
  })
}

// 查询销售出库UDI信息详细
export function getOutboundUDI(id) {
  return request({
    url: '/trace/outboundUDI/' + id,
    method: 'get'
  })
}

// 新增销售出库UDI信息
export function addOutboundUDI(data) {
  return request({
    url: '/trace/outboundUDI',
    method: 'post',
    data: data
  })
}

// 修改销售出库UDI信息
export function updateOutboundUDI(data) {
  return request({
    url: '/trace/outboundUDI',
    method: 'put',
    data: data
  })
}

// 删除销售出库UDI信息
export function delOutboundUDI(id) {
  return request({
    url: '/trace/outboundUDI/' + id,
    method: 'delete'
  })
}

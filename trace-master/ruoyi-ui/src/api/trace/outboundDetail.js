import request from '@/utils/request'

// 查询销售出库明细列表
export function listOutboundDetail(query) {
  return request({
    url: '/trace/outboundDetail/list',
    method: 'get',
    params: query
  })
}

// 查询销售出库明细详细
export function getOutboundDetail(id) {
  return request({
    url: '/trace/outboundDetail/' + id,
    method: 'get'
  })
}

// 新增销售出库明细
export function addOutboundDetail(data) {
  return request({
    url: '/trace/outboundDetail',
    method: 'post',
    data: data
  })
}

// 修改销售出库明细
export function updateOutboundDetail(data) {
  return request({
    url: '/trace/outboundDetail',
    method: 'put',
    data: data
  })
}

// 删除销售出库明细
export function delOutboundDetail(id) {
  return request({
    url: '/trace/outboundDetail/' + id,
    method: 'delete'
  })
}

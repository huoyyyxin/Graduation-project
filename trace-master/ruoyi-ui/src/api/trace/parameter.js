import request from '@/utils/request'

// 查询parameter列表
export function listParameter(query) {
  return request({
    url: '/trace/parameter/list',
    method: 'get',
    params: query
  })
}

// 查询parameter详细
export function getParameter(systemParameterId) {
  return request({
    url: '/trace/parameter/' + systemParameterId,
    method: 'get'
  })
}

// 新增parameter
export function addParameter(data) {
  return request({
    url: '/trace/parameter',
    method: 'post',
    data: data
  })
}

// 修改parameter
export function updateParameter(data) {
  return request({
    url: '/trace/parameter',
    method: 'put',
    data: data
  })
}

// 删除parameter
export function delParameter(systemParameterId) {
  return request({
    url: '/trace/parameter/' + systemParameterId,
    method: 'delete'
  })
}

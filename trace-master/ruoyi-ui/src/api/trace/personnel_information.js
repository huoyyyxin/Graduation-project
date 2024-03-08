import request from '@/utils/request'

// 查询personnel_information列表
export function listPersonnel_information(query) {
  return request({
    url: '/trace/personnel_information/list',
    method: 'get',
    params: query
  })
}

// 查询personnel_information详细
export function getPersonnel_information(personnelInformationId) {
  return request({
    url: '/trace/personnel_information/' + personnelInformationId,
    method: 'get'
  })
}

// 新增personnel_information
export function addPersonnel_information(data) {
  return request({
    url: '/trace/personnel_information',
    method: 'post',
    data: data
  })
}

// 修改personnel_information
export function updatePersonnel_information(data) {
  return request({
    url: '/trace/personnel_information',
    method: 'put',
    data: data
  })
}

// 删除personnel_information
export function delPersonnel_information(personnelInformationId) {
  return request({
    url: '/trace/personnel_information/' + personnelInformationId,
    method: 'delete'
  })
}

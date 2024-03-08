import request from '@/utils/request'

// 查询BOM信息列表
export function listBom(query) {
  return request({
    url: '/bom/bom/list',
    method: 'get',
    params: query
  })
}

// 查询BOM信息详细
export function getBom(productId) {
  return request({
    url: '/bom/bom/' + productId,
    method: 'get'
  })
}

// 新增BOM信息
export function addBom(data) {
  return request({
    url: '/bom/bom',
    method: 'post',
    data: data
  })
}

// 修改BOM信息
export function updateBom(data) {
  return request({
    url: '/bom/bom',
    method: 'put',
    data: data
  })
}

// 删除BOM信息
export function delBom(productId) {
  return request({
    url: '/bom/bom/' + productId,
    method: 'delete'
  })
}

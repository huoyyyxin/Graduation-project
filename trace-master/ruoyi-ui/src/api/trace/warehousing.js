import request from '@/utils/request'

// 查询生产入库列表
export function listWarehousing(query) {
  return request({
    url: '/warehousing/warehousing/list',
    method: 'get',
    params: query
  })
}

// 查询生产入库详细
export function getWarehousing(id) {
  return request({
    url: '/warehousing/warehousing/' + id,
    method: 'get'
  })
}

// 新增生产入库
export function addWarehousing(data) {
  return request({
    url: '/warehousing/warehousing',
    method: 'post',
    data: data
  })
}

// 修改生产入库
export function updateWarehousing(data) {
  return request({
    url: '/warehousing/warehousing',
    method: 'put',
    data: data
  })
}

// 删除生产入库
export function delWarehousing(id) {
  return request({
    url: '/warehousing/warehousing/' + id,
    method: 'delete'
  })
}

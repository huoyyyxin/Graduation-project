import request from '@/utils/request'

// 查询生产入库UDI明细列表
export function listUdi(query) {
  return request({
    url: '/warehousing/udi/list',
    method: 'get',
    params: query
  })
}

// 查询生产入库UDI明细详细
export function getUdi(id) {
  return request({
    url: '/warehousing/udi/' + id,
    method: 'get'
  })
}
//拿到生产入库单编号
export function listWarehousingCode() {
  return request({
    url: '/warehousing/warehousing/listCode',
    method: 'get'
  })
}

// 新增生产入库UDI明细
export function addUdi(data) {
  return request({
    url: '/warehousing/udi',
    method: 'post',
    data: data
  })
}

// 修改生产入库UDI明细
export function updateUdi(data) {
  return request({
    url: '/warehousing/udi',
    method: 'put',
    data: data
  })
}

// 删除生产入库UDI明细
export function delUdi(id) {
  return request({
    url: '/warehousing/udi/' + id,
    method: 'delete'
  })
}

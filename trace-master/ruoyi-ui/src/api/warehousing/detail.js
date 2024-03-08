import request from '@/utils/request'

// 查询生产入库明细列表
export function listDetail(query) {
  return request({
    url: '/warehousing/detail/list',
    method: 'get',
    params: query
  })
}
//拿到生产入库单编号
export function listWarehousingCode() {
  return request({
    url: '/warehousing/warehousing/listCode',
    method: 'get'
  })
}

export function listDetailAll() {
  return request({
    url: '/warehousing/detail/listAll',
    method: 'get'
  })
}

// 查询生产入库明细详细
export function getDetail(id) {
  return request({
    url: '/warehousing/detail/' + id,
    method: 'get'
  })
}

// 新增生产入库明细
export function addDetail(data) {
  return request({
    url: '/warehousing/detail',
    method: 'post',
    data: data
  })
}

// 修改生产入库明细
export function updateDetail(data) {
  return request({
    url: '/warehousing/detail',
    method: 'put',
    data: data
  })
}

// 删除生产入库明细
export function delDetail(id) {
  return request({
    url: '/warehousing/detail/' + id,
    method: 'delete'
  })
}

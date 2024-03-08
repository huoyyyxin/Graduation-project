package com.ruoyi.trace.service;

import java.util.List;
import com.ruoyi.trace.domain.TraceProductWarehousingDetail;
import com.ruoyi.trace.domain.TraceProductionWarehousing;

/**
 * 生产入库明细Service接口
 * 
 * @author ruoyi
 * @date 2024-02-24
 */
public interface ITraceProductWarehousingDetailService 
{
    /**
     * 查询生产入库明细
     * 
     * @param id 生产入库明细主键
     * @return 生产入库明细
     */
    public TraceProductWarehousingDetail selectTraceProductWarehousingDetailById(Long id);

    /**
     * 查询生产入库明细列表
     * 
     * @param traceProductWarehousingDetail 生产入库明细
     * @return 生产入库明细集合
     */
    public List<TraceProductWarehousingDetail> selectTraceProductWarehousingDetailList(TraceProductWarehousingDetail traceProductWarehousingDetail);

    /**
     * 新增生产入库明细
     * 
     * @param traceProductWarehousingDetail 生产入库明细
     * @return 结果
     */
    public int insertTraceProductWarehousingDetail(TraceProductWarehousingDetail traceProductWarehousingDetail);

    /**
     * 修改生产入库明细
     * 
     * @param traceProductWarehousingDetail 生产入库明细
     * @return 结果
     */
    public int updateTraceProductWarehousingDetail(TraceProductWarehousingDetail traceProductWarehousingDetail);

    /**
     * 批量删除生产入库明细
     * 
     * @param ids 需要删除的生产入库明细主键集合
     * @return 结果
     */
    public int deleteTraceProductWarehousingDetailByIds(Long[] ids);

    /**
     * 删除生产入库明细信息
     * 
     * @param id 生产入库明细主键
     * @return 结果
     */
    public int deleteTraceProductWarehousingDetailById(Long id);

    List<TraceProductWarehousingDetail> getProductionWarehousing(String id);

    List<TraceProductWarehousingDetail> getDetail(String productionWarehousingCode);


    TraceProductWarehousingDetail selectDetailWithCode(String code);

    public int deleteTraceProductionWarehousingDetailByTpwid(String[] ids);
}

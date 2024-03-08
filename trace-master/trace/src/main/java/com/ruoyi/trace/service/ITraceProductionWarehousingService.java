package com.ruoyi.trace.service;

import java.util.List;
import com.ruoyi.trace.domain.TraceProductionWarehousing;

/**
 * 生产入库Service接口
 * 
 * @author ruoyi
 * @date 2024-02-26
 */
public interface ITraceProductionWarehousingService 
{
    /**
     * 查询生产入库
     * 
     * @param id 生产入库主键
     * @return 生产入库
     */
    public TraceProductionWarehousing selectTraceProductionWarehousingById(Long id);

    /**
     * 查询生产入库列表
     * 
     * @param traceProductionWarehousing 生产入库
     * @return 生产入库集合
     */
    public List<TraceProductionWarehousing> selectTraceProductionWarehousingList(TraceProductionWarehousing traceProductionWarehousing);

    /**
     * 查询入库列表精确查询
     *
     * @param traceProductionWarehousing 生产入库
     * @return 生产入库集合
     */
    public List<TraceProductionWarehousing> selectTraceProductionWarehousingListEqual(TraceProductionWarehousing traceProductionWarehousing);

    /**
     * 新增生产入库
     * 
     * @param traceProductionWarehousing 生产入库
     * @return 结果
     */
    public int insertTraceProductionWarehousing(TraceProductionWarehousing traceProductionWarehousing);

    /**
     * 修改生产入库
     * 
     * @param traceProductionWarehousing 生产入库
     * @return 结果
     */
    public int updateTraceProductionWarehousing(TraceProductionWarehousing traceProductionWarehousing);

    /**
     * 批量删除生产入库
     * 
     * @param ids 需要删除的生产入库主键集合
     * @return 结果
     */
    public int deleteTraceProductionWarehousingByIds(Long[] ids);

    /**
     * 删除生产入库信息
     * 
     * @param id 生产入库主键
     * @return 结果
     */
    public int deleteTraceProductionWarehousingById(Long id);

    List<TraceProductionWarehousing> selectAllCode(Long[] ids);
}

package com.ruoyi.trace.mapper;

import java.util.List;
import com.ruoyi.trace.domain.TraceProductionWarehousing;

/**
 * 生产入库Mapper接口
 * 
 * @author ruoyi
 * @date 2024-02-26
 */
public interface TraceProductionWarehousingMapper 
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
     * 删除生产入库
     * 
     * @param id 生产入库主键
     * @return 结果
     */
    public int deleteTraceProductionWarehousingById(Long id);

    /**
     * 批量删除生产入库
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTraceProductionWarehousingByIds(Long[] ids);

    public List<TraceProductionWarehousing> selectAllCode(Long[] ids);
}

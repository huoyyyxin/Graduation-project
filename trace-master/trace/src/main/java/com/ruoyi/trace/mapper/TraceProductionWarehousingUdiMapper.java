package com.ruoyi.trace.mapper;

import java.util.List;

import com.ruoyi.trace.domain.TraceProductWarehousingDetail;
import com.ruoyi.trace.domain.TraceProductionWarehousingUdi;

/**
 * 生产入库UDI明细Mapper接口
 * 
 * @author ruoyi
 * @date 2024-02-26
 */
public interface TraceProductionWarehousingUdiMapper 
{
    /**
     * 查询生产入库UDI明细
     * 
     * @param id 生产入库UDI明细主键
     * @return 生产入库UDI明细
     */
    public TraceProductionWarehousingUdi selectTraceProductionWarehousingUdiById(Long id);

    /**
     * 查询生产入库UDI明细列表
     * 
     * @param traceProductionWarehousingUdi 生产入库UDI明细
     * @return 生产入库UDI明细集合
     */
    public List<TraceProductionWarehousingUdi> selectTraceProductionWarehousingUdiList(TraceProductionWarehousingUdi traceProductionWarehousingUdi);

    /**
     * 新增生产入库UDI明细
     * 
     * @param traceProductionWarehousingUdi 生产入库UDI明细
     * @return 结果
     */
    public int insertTraceProductionWarehousingUdi(TraceProductionWarehousingUdi traceProductionWarehousingUdi);

    /**
     * 修改生产入库UDI明细
     * 
     * @param traceProductionWarehousingUdi 生产入库UDI明细
     * @return 结果
     */
    public int updateTraceProductionWarehousingUdi(TraceProductionWarehousingUdi traceProductionWarehousingUdi);

    /**
     * 删除生产入库UDI明细
     * 
     * @param id 生产入库UDI明细主键
     * @return 结果
     */
    public int deleteTraceProductionWarehousingUdiById(Long id);

    /**
     * 批量删除生产入库UDI明细
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTraceProductionWarehousingUdiByIds(Long[] ids);

    List<TraceProductionWarehousingUdi> selectUdiWithCode(String warehousingCode);

    /**
     * 根据tpwid删除
     */
    public int deleteTraceProductionWarehousingByTpwid(String[] ids);
}

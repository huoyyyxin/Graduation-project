package com.ruoyi.trace.service;

import java.util.List;
import com.ruoyi.trace.domain.TraceProductionWarehousingUdi;

/**
 * 生产入库UDI明细Service接口
 * 
 * @author ruoyi
 * @date 2024-02-26
 */
public interface ITraceProductionWarehousingUdiService 
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
     * 批量删除生产入库UDI明细
     * 
     * @param ids 需要删除的生产入库UDI明细主键集合
     * @return 结果
     */
    public int deleteTraceProductionWarehousingUdiByIds(Long[] ids);

    /**
     * 删除生产入库UDI明细信息
     * 
     * @param id 生产入库UDI明细主键
     * @return 结果
     */
    public int deleteTraceProductionWarehousingUdiById(Long id);
    //根据 code 查询 udi
    List<TraceProductionWarehousingUdi> selectUdiListWithCode(String warehousingCode);

    List<TraceProductionWarehousingUdi> selectUdiWithCode(String code);

    public int deleteTraceProductionWarehousingByTpwid(String[] ids);

}

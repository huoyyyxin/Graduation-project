package com.ruoyi.trace.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.trace.mapper.TraceProductionWarehousingMapper;
import com.ruoyi.trace.domain.TraceProductionWarehousing;
import com.ruoyi.trace.service.ITraceProductionWarehousingService;

/**
 * 生产入库Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-02-26
 */
@Service
public class TraceProductionWarehousingServiceImpl implements ITraceProductionWarehousingService 
{
    @Autowired
    private TraceProductionWarehousingMapper traceProductionWarehousingMapper;

    /**
     * 查询生产入库
     * 
     * @param id 生产入库主键
     * @return 生产入库
     */
    @Override
    public TraceProductionWarehousing selectTraceProductionWarehousingById(Long id)
    {
        return traceProductionWarehousingMapper.selectTraceProductionWarehousingById(id);
    }

    /**
     * 查询生产入库列表
     * 
     * @param traceProductionWarehousing 生产入库
     * @return 生产入库
     */
    @Override
    public List<TraceProductionWarehousing> selectTraceProductionWarehousingList(TraceProductionWarehousing traceProductionWarehousing)
    {
        return traceProductionWarehousingMapper.selectTraceProductionWarehousingList(traceProductionWarehousing);
    }
    /**
     * 查询入库列表精确查询
     *
     * @param traceProductionWarehousing 生产入库
     * @return 生产入库集合
     */
    @Override
    public List<TraceProductionWarehousing> selectTraceProductionWarehousingListEqual(TraceProductionWarehousing traceProductionWarehousing)
    {
        return traceProductionWarehousingMapper.selectTraceProductionWarehousingListEqual(traceProductionWarehousing);
    }
    /**
     * 新增生产入库
     * 
     * @param traceProductionWarehousing 生产入库
     * @return 结果
     */
    @Override
    public int insertTraceProductionWarehousing(TraceProductionWarehousing traceProductionWarehousing)
    {
        traceProductionWarehousing.setCreateTime(DateUtils.getNowDate());
        return traceProductionWarehousingMapper.insertTraceProductionWarehousing(traceProductionWarehousing);
    }

    /**
     * 修改生产入库
     * 
     * @param traceProductionWarehousing 生产入库
     * @return 结果
     */
    @Override
    public int updateTraceProductionWarehousing(TraceProductionWarehousing traceProductionWarehousing)
    {
        traceProductionWarehousing.setUpdateTime(DateUtils.getNowDate());
        return traceProductionWarehousingMapper.updateTraceProductionWarehousing(traceProductionWarehousing);
    }

    /**
     * 批量删除生产入库
     * 
     * @param ids 需要删除的生产入库主键
     * @return 结果
     */
    @Override
    public int deleteTraceProductionWarehousingByIds(Long[] ids)
    {
        return traceProductionWarehousingMapper.deleteTraceProductionWarehousingByIds(ids);
    }

    /**
     * 删除生产入库信息
     * 
     * @param id 生产入库主键
     * @return 结果
     */
    @Override
    public int deleteTraceProductionWarehousingById(Long id)
    {
        return traceProductionWarehousingMapper.deleteTraceProductionWarehousingById(id);
    }

    @Override
    public List<TraceProductionWarehousing> selectAllCode(Long[] ids) {
        List<TraceProductionWarehousing> traceProductionWarehousings = traceProductionWarehousingMapper.selectAllCode(ids);
        return traceProductionWarehousings;
    }
}

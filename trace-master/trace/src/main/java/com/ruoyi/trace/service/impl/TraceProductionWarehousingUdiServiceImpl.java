package com.ruoyi.trace.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.trace.domain.TraceProductWarehousingDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.trace.mapper.TraceProductionWarehousingUdiMapper;
import com.ruoyi.trace.domain.TraceProductionWarehousingUdi;
import com.ruoyi.trace.service.ITraceProductionWarehousingUdiService;

/**
 * 生产入库UDI明细Service业务层处理
 *
 * @author ruoyi
 * @date 2024-02-26
 */
@Service
public class TraceProductionWarehousingUdiServiceImpl implements ITraceProductionWarehousingUdiService {
    @Autowired
    private TraceProductionWarehousingUdiMapper traceProductionWarehousingUdiMapper;

    /**
     * 查询生产入库UDI明细
     *
     * @param id 生产入库UDI明细主键
     * @return 生产入库UDI明细
     */
    @Override
    public TraceProductionWarehousingUdi selectTraceProductionWarehousingUdiById(Long id) {
        return traceProductionWarehousingUdiMapper.selectTraceProductionWarehousingUdiById(id);
    }

    /**
     * 查询生产入库UDI明细列表
     *
     * @param traceProductionWarehousingUdi 生产入库UDI明细
     * @return 生产入库UDI明细
     */
    @Override
    public List<TraceProductionWarehousingUdi> selectTraceProductionWarehousingUdiList(TraceProductionWarehousingUdi traceProductionWarehousingUdi) {
        return traceProductionWarehousingUdiMapper.selectTraceProductionWarehousingUdiList(traceProductionWarehousingUdi);
    }

    /**
     * 新增生产入库UDI明细
     *
     * @param traceProductionWarehousingUdi 生产入库UDI明细
     * @return 结果
     */
    @Override
    public int insertTraceProductionWarehousingUdi(TraceProductionWarehousingUdi traceProductionWarehousingUdi) {
        traceProductionWarehousingUdi.setCreateTime(DateUtils.getNowDate());
        return traceProductionWarehousingUdiMapper.insertTraceProductionWarehousingUdi(traceProductionWarehousingUdi);
    }

    /**
     * 修改生产入库UDI明细
     *
     * @param traceProductionWarehousingUdi 生产入库UDI明细
     * @return 结果
     */
    @Override
    public int updateTraceProductionWarehousingUdi(TraceProductionWarehousingUdi traceProductionWarehousingUdi) {
        traceProductionWarehousingUdi.setUpdateTime(DateUtils.getNowDate());
        return traceProductionWarehousingUdiMapper.updateTraceProductionWarehousingUdi(traceProductionWarehousingUdi);
    }

    /**
     * 批量删除生产入库UDI明细
     *
     * @param ids 需要删除的生产入库UDI明细主键
     * @return 结果
     */
    @Override
    public int deleteTraceProductionWarehousingUdiByIds(Long[] ids) {
        return traceProductionWarehousingUdiMapper.deleteTraceProductionWarehousingUdiByIds(ids);
    }

    /**
     * 删除生产入库UDI明细信息
     *
     * @param id 生产入库UDI明细主键
     * @return 结果
     */
    @Override
    public int deleteTraceProductionWarehousingUdiById(Long id) {
        return traceProductionWarehousingUdiMapper.deleteTraceProductionWarehousingUdiById(id);
    }

    @Override
    public List<TraceProductionWarehousingUdi> selectUdiListWithCode(String warehousingCode) {
        List<TraceProductionWarehousingUdi> traceProductWarehousingUdi = traceProductionWarehousingUdiMapper.selectUdiWithCode(warehousingCode);
        return traceProductWarehousingUdi;
    }

    @Override
    public List<TraceProductionWarehousingUdi> selectUdiWithCode(String code) {
        return traceProductionWarehousingUdiMapper.selectUdiWithCode(code);
    }

    @Override
    public int deleteTraceProductionWarehousingByTpwid(String[] ids) {
        return traceProductionWarehousingUdiMapper.deleteTraceProductionWarehousingByTpwid(ids);
    }
}

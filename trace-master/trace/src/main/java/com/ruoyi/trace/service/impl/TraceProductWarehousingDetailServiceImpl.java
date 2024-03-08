package com.ruoyi.trace.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.trace.mapper.TraceProductWarehousingDetailMapper;
import com.ruoyi.trace.domain.TraceProductWarehousingDetail;
import com.ruoyi.trace.service.ITraceProductWarehousingDetailService;

/**
 * 生产入库明细Service业务层处理
 *
 * @author ruoyi
 * @date 2024-02-24
 */
@Service
public class TraceProductWarehousingDetailServiceImpl implements ITraceProductWarehousingDetailService
{
    @Autowired
    private TraceProductWarehousingDetailMapper traceProductWarehousingDetailMapper;

    /**
     * 查询生产入库明细
     *
     * @param id 生产入库明细主键
     * @return 生产入库明细
     */
    @Override
    public TraceProductWarehousingDetail selectTraceProductWarehousingDetailById(Long id)
    {
        return traceProductWarehousingDetailMapper.selectTraceProductWarehousingDetailById(id);
    }

    /**
     * 查询生产入库明细列表
     *
     * @param traceProductWarehousingDetail 生产入库明细
     * @return 生产入库明细
     */
    @Override
    public List<TraceProductWarehousingDetail> selectTraceProductWarehousingDetailList(TraceProductWarehousingDetail traceProductWarehousingDetail)
    {
        return traceProductWarehousingDetailMapper.selectTraceProductWarehousingDetailList(traceProductWarehousingDetail);
    }

    /**
     * 新增生产入库明细
     *
     * @param traceProductWarehousingDetail 生产入库明细
     * @return 结果
     */
    @Override
    public int insertTraceProductWarehousingDetail(TraceProductWarehousingDetail traceProductWarehousingDetail)
    {
        traceProductWarehousingDetail.setCreateTime(DateUtils.getNowDate());
        return traceProductWarehousingDetailMapper.insertTraceProductWarehousingDetail(traceProductWarehousingDetail);
    }

    /**
     * 修改生产入库明细
     *
     * @param traceProductWarehousingDetail 生产入库明细
     * @return 结果
     */
    @Override
    public int updateTraceProductWarehousingDetail(TraceProductWarehousingDetail traceProductWarehousingDetail)
    {
        traceProductWarehousingDetail.setUpdateTime(DateUtils.getNowDate());
        return traceProductWarehousingDetailMapper.updateTraceProductWarehousingDetail(traceProductWarehousingDetail);
    }

    /**
     * 批量删除生产入库明细
     *
     * @param ids 需要删除的生产入库明细主键
     * @return 结果
     */
    @Override
    public int deleteTraceProductWarehousingDetailByIds(Long[] ids)
    {
        return traceProductWarehousingDetailMapper.deleteTraceProductWarehousingDetailByIds(ids);
    }

    /**
     * 删除生产入库明细信息
     *
     * @param id 生产入库明细主键
     * @return 结果
     */
    @Override
    public int deleteTraceProductWarehousingDetailById(Long id)
    {
        return traceProductWarehousingDetailMapper.deleteTraceProductWarehousingDetailById(id);
    }

    @Override
    public List<TraceProductWarehousingDetail> getProductionWarehousing(String id) {
        List<TraceProductWarehousingDetail> traceProductWarehousingDetails = traceProductWarehousingDetailMapper.getProductionWarehousing(id);
        return traceProductWarehousingDetails;
    }

    @Override
    public List<TraceProductWarehousingDetail> getDetail(String productionWarehousingCode) {
        List<TraceProductWarehousingDetail> traceProductWarehousingDetails = traceProductWarehousingDetailMapper.getDetail(productionWarehousingCode);
        return traceProductWarehousingDetails;
    }

    @Override
    public TraceProductWarehousingDetail selectDetailWithCode(String code) {
        return traceProductWarehousingDetailMapper.selectDetailWithCode(code);
    }

    @Override
    public int deleteTraceProductionWarehousingDetailByTpwid(String[] ids) {
        return traceProductWarehousingDetailMapper.deleteTraceProductionWarehousingDetailByTpwid(ids);
    }
}

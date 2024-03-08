package com.ruoyi.trace.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.trace.mapper.SaleOutboundDetailMapper;
import com.ruoyi.trace.domain.SaleOutboundDetail;
import com.ruoyi.trace.service.ISaleOutboundDetailService;

/**
 * 销售出库明细Service业务层处理
 * 
 * @author zhang
 * @date 2024-03-08
 */
@Service
public class SaleOutboundDetailServiceImpl implements ISaleOutboundDetailService 
{
    @Autowired
    private SaleOutboundDetailMapper saleOutboundDetailMapper;

    /**
     * 查询销售出库明细
     * 
     * @param id 销售出库明细主键
     * @return 销售出库明细
     */
    @Override
    public SaleOutboundDetail selectSaleOutboundDetailById(Long id)
    {
        return saleOutboundDetailMapper.selectSaleOutboundDetailById(id);
    }

    /**
     * 查询销售出库明细列表
     * 
     * @param saleOutboundDetail 销售出库明细
     * @return 销售出库明细
     */
    @Override
    public List<SaleOutboundDetail> selectSaleOutboundDetailList(SaleOutboundDetail saleOutboundDetail)
    {
        return saleOutboundDetailMapper.selectSaleOutboundDetailList(saleOutboundDetail);
    }

    /**
     * 新增销售出库明细
     * 
     * @param saleOutboundDetail 销售出库明细
     * @return 结果
     */
    @Override
    public int insertSaleOutboundDetail(SaleOutboundDetail saleOutboundDetail)
    {
        saleOutboundDetail.setCreateTime(DateUtils.getNowDate());
        return saleOutboundDetailMapper.insertSaleOutboundDetail(saleOutboundDetail);
    }

    /**
     * 修改销售出库明细
     * 
     * @param saleOutboundDetail 销售出库明细
     * @return 结果
     */
    @Override
    public int updateSaleOutboundDetail(SaleOutboundDetail saleOutboundDetail)
    {
        saleOutboundDetail.setUpdateTime(DateUtils.getNowDate());
        return saleOutboundDetailMapper.updateSaleOutboundDetail(saleOutboundDetail);
    }

    /**
     * 批量删除销售出库明细
     * 
     * @param ids 需要删除的销售出库明细主键
     * @return 结果
     */
    @Override
    public int deleteSaleOutboundDetailByIds(Long[] ids)
    {
        return saleOutboundDetailMapper.deleteSaleOutboundDetailByIds(ids);
    }

    /**
     * 删除销售出库明细信息
     * 
     * @param id 销售出库明细主键
     * @return 结果
     */
    @Override
    public int deleteSaleOutboundDetailById(Long id)
    {
        return saleOutboundDetailMapper.deleteSaleOutboundDetailById(id);
    }
}

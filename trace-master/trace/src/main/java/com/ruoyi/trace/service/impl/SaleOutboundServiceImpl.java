package com.ruoyi.trace.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.trace.mapper.SaleOutboundMapper;
import com.ruoyi.trace.domain.SaleOutbound;
import com.ruoyi.trace.service.ISaleOutboundService;

/**
 * 销售出库Service业务层处理
 * 
 * @author zhang
 * @date 2024-03-08
 */
@Service
public class SaleOutboundServiceImpl implements ISaleOutboundService 
{
    @Autowired
    private SaleOutboundMapper saleOutboundMapper;

    /**
     * 查询销售出库
     * 
     * @param id 销售出库主键
     * @return 销售出库
     */
    @Override
    public SaleOutbound selectSaleOutboundById(Long id)
    {
        return saleOutboundMapper.selectSaleOutboundById(id);
    }

    /**
     * 查询销售出库列表
     * 
     * @param saleOutbound 销售出库
     * @return 销售出库
     */
    @Override
    public List<SaleOutbound> selectSaleOutboundList(SaleOutbound saleOutbound)
    {
        return saleOutboundMapper.selectSaleOutboundList(saleOutbound);
    }

    /**
     * 新增销售出库
     * 
     * @param saleOutbound 销售出库
     * @return 结果
     */
    @Override
    public int insertSaleOutbound(SaleOutbound saleOutbound)
    {
        saleOutbound.setCreateTime(DateUtils.getNowDate());
        return saleOutboundMapper.insertSaleOutbound(saleOutbound);
    }

    /**
     * 修改销售出库
     * 
     * @param saleOutbound 销售出库
     * @return 结果
     */
    @Override
    public int updateSaleOutbound(SaleOutbound saleOutbound)
    {
        saleOutbound.setUpdateTime(DateUtils.getNowDate());
        return saleOutboundMapper.updateSaleOutbound(saleOutbound);
    }

    /**
     * 批量删除销售出库
     * 
     * @param ids 需要删除的销售出库主键
     * @return 结果
     */
    @Override
    public int deleteSaleOutboundByIds(Long[] ids)
    {
        return saleOutboundMapper.deleteSaleOutboundByIds(ids);
    }

    /**
     * 删除销售出库信息
     * 
     * @param id 销售出库主键
     * @return 结果
     */
    @Override
    public int deleteSaleOutboundById(Long id)
    {
        return saleOutboundMapper.deleteSaleOutboundById(id);
    }
}

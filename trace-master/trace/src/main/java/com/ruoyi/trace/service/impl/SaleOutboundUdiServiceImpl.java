package com.ruoyi.trace.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.trace.mapper.SaleOutboundUdiMapper;
import com.ruoyi.trace.domain.SaleOutboundUdi;
import com.ruoyi.trace.service.ISaleOutboundUdiService;

/**
 * 销售出库UDI信息Service业务层处理
 * 
 * @author zhang
 * @date 2024-03-08
 */
@Service
public class SaleOutboundUdiServiceImpl implements ISaleOutboundUdiService 
{
    @Autowired
    private SaleOutboundUdiMapper saleOutboundUdiMapper;

    /**
     * 查询销售出库UDI信息
     * 
     * @param id 销售出库UDI信息主键
     * @return 销售出库UDI信息
     */
    @Override
    public SaleOutboundUdi selectSaleOutboundUdiById(Long id)
    {
        return saleOutboundUdiMapper.selectSaleOutboundUdiById(id);
    }

    /**
     * 查询销售出库UDI信息列表
     * 
     * @param saleOutboundUdi 销售出库UDI信息
     * @return 销售出库UDI信息
     */
    @Override
    public List<SaleOutboundUdi> selectSaleOutboundUdiList(SaleOutboundUdi saleOutboundUdi)
    {
        return saleOutboundUdiMapper.selectSaleOutboundUdiList(saleOutboundUdi);
    }

    /**
     * 新增销售出库UDI信息
     * 
     * @param saleOutboundUdi 销售出库UDI信息
     * @return 结果
     */
    @Override
    public int insertSaleOutboundUdi(SaleOutboundUdi saleOutboundUdi)
    {
        saleOutboundUdi.setCreateTime(DateUtils.getNowDate());
        return saleOutboundUdiMapper.insertSaleOutboundUdi(saleOutboundUdi);
    }

    /**
     * 修改销售出库UDI信息
     * 
     * @param saleOutboundUdi 销售出库UDI信息
     * @return 结果
     */
    @Override
    public int updateSaleOutboundUdi(SaleOutboundUdi saleOutboundUdi)
    {
        saleOutboundUdi.setUpdateTime(DateUtils.getNowDate());
        return saleOutboundUdiMapper.updateSaleOutboundUdi(saleOutboundUdi);
    }

    /**
     * 批量删除销售出库UDI信息
     * 
     * @param ids 需要删除的销售出库UDI信息主键
     * @return 结果
     */
    @Override
    public int deleteSaleOutboundUdiByIds(Long[] ids)
    {
        return saleOutboundUdiMapper.deleteSaleOutboundUdiByIds(ids);
    }

    /**
     * 删除销售出库UDI信息信息
     * 
     * @param id 销售出库UDI信息主键
     * @return 结果
     */
    @Override
    public int deleteSaleOutboundUdiById(Long id)
    {
        return saleOutboundUdiMapper.deleteSaleOutboundUdiById(id);
    }
}

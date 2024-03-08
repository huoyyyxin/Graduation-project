package com.ruoyi.trace.service;

import java.util.List;
import com.ruoyi.trace.domain.SaleOutboundUdi;

/**
 * 销售出库UDI信息Service接口
 * 
 * @author zhang
 * @date 2024-03-08
 */
public interface ISaleOutboundUdiService 
{
    /**
     * 查询销售出库UDI信息
     * 
     * @param id 销售出库UDI信息主键
     * @return 销售出库UDI信息
     */
    public SaleOutboundUdi selectSaleOutboundUdiById(Long id);

    /**
     * 查询销售出库UDI信息列表
     * 
     * @param saleOutboundUdi 销售出库UDI信息
     * @return 销售出库UDI信息集合
     */
    public List<SaleOutboundUdi> selectSaleOutboundUdiList(SaleOutboundUdi saleOutboundUdi);

    /**
     * 新增销售出库UDI信息
     * 
     * @param saleOutboundUdi 销售出库UDI信息
     * @return 结果
     */
    public int insertSaleOutboundUdi(SaleOutboundUdi saleOutboundUdi);

    /**
     * 修改销售出库UDI信息
     * 
     * @param saleOutboundUdi 销售出库UDI信息
     * @return 结果
     */
    public int updateSaleOutboundUdi(SaleOutboundUdi saleOutboundUdi);

    /**
     * 批量删除销售出库UDI信息
     * 
     * @param ids 需要删除的销售出库UDI信息主键集合
     * @return 结果
     */
    public int deleteSaleOutboundUdiByIds(Long[] ids);

    /**
     * 删除销售出库UDI信息信息
     * 
     * @param id 销售出库UDI信息主键
     * @return 结果
     */
    public int deleteSaleOutboundUdiById(Long id);
}

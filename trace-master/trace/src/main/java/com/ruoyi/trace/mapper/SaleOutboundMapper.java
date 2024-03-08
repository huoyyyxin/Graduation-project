package com.ruoyi.trace.mapper;

import java.util.List;
import com.ruoyi.trace.domain.SaleOutbound;

/**
 * 销售出库Mapper接口
 * 
 * @author zhang
 * @date 2024-03-08
 */
public interface SaleOutboundMapper 
{
    /**
     * 查询销售出库
     * 
     * @param id 销售出库主键
     * @return 销售出库
     */
    public SaleOutbound selectSaleOutboundById(Long id);

    /**
     * 查询销售出库列表
     * 
     * @param saleOutbound 销售出库
     * @return 销售出库集合
     */
    public List<SaleOutbound> selectSaleOutboundList(SaleOutbound saleOutbound);

    /**
     * 新增销售出库
     * 
     * @param saleOutbound 销售出库
     * @return 结果
     */
    public int insertSaleOutbound(SaleOutbound saleOutbound);

    /**
     * 修改销售出库
     * 
     * @param saleOutbound 销售出库
     * @return 结果
     */
    public int updateSaleOutbound(SaleOutbound saleOutbound);

    /**
     * 删除销售出库
     * 
     * @param id 销售出库主键
     * @return 结果
     */
    public int deleteSaleOutboundById(Long id);

    /**
     * 批量删除销售出库
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSaleOutboundByIds(Long[] ids);
}

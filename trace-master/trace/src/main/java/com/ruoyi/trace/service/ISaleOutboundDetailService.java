package com.ruoyi.trace.service;

import java.util.List;
import com.ruoyi.trace.domain.SaleOutboundDetail;

/**
 * 销售出库明细Service接口
 * 
 * @author zhang
 * @date 2024-03-08
 */
public interface ISaleOutboundDetailService 
{
    /**
     * 查询销售出库明细
     * 
     * @param id 销售出库明细主键
     * @return 销售出库明细
     */
    public SaleOutboundDetail selectSaleOutboundDetailById(Long id);

    /**
     * 查询销售出库明细列表
     * 
     * @param saleOutboundDetail 销售出库明细
     * @return 销售出库明细集合
     */
    public List<SaleOutboundDetail> selectSaleOutboundDetailList(SaleOutboundDetail saleOutboundDetail);

    /**
     * 新增销售出库明细
     * 
     * @param saleOutboundDetail 销售出库明细
     * @return 结果
     */
    public int insertSaleOutboundDetail(SaleOutboundDetail saleOutboundDetail);

    /**
     * 修改销售出库明细
     * 
     * @param saleOutboundDetail 销售出库明细
     * @return 结果
     */
    public int updateSaleOutboundDetail(SaleOutboundDetail saleOutboundDetail);

    /**
     * 批量删除销售出库明细
     * 
     * @param ids 需要删除的销售出库明细主键集合
     * @return 结果
     */
    public int deleteSaleOutboundDetailByIds(Long[] ids);

    /**
     * 删除销售出库明细信息
     * 
     * @param id 销售出库明细主键
     * @return 结果
     */
    public int deleteSaleOutboundDetailById(Long id);
}

package com.ruoyi.trace.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 销售出库明细对象 trace_sale_outbound_detail
 * 
 * @author zhang
 * @date 2024-03-08
 */
public class SaleOutboundDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 销售出库单编号 */
    @Excel(name = "销售出库单编号")
    private String saleOutboundId;

    /** 产品品类编号 */
    @Excel(name = "产品品类编号")
    private String productCategoryId;

    /** 数量 */
    @Excel(name = "数量")
    private Long outboundQuantity;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSaleOutboundId(String saleOutboundId)
    {
        this.saleOutboundId = saleOutboundId;
    }

    public String getSaleOutboundId()
    {
        return saleOutboundId;
    }
    public void setProductCategoryId(String productCategoryId) 
    {
        this.productCategoryId = productCategoryId;
    }

    public String getProductCategoryId() 
    {
        return productCategoryId;
    }
    public void setOutboundQuantity(Long outboundQuantity) 
    {
        this.outboundQuantity = outboundQuantity;
    }

    public Long getOutboundQuantity() 
    {
        return outboundQuantity;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sale OutboundId", getSaleOutboundId())
            .append("productCategoryId", getProductCategoryId())
            .append("outboundQuantity", getOutboundQuantity())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

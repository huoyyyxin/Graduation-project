package com.ruoyi.trace.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 生产入库明细对象 trace_product_warehousing_detail
 * 
 * @author ruoyi
 * @date 2024-02-24
 */
public class TraceProductWarehousingDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 生产入库单编号 */
    @Excel(name = "生产入库单编号")
    private String productionWarehousingCode;

    /** 产品品类编号 */
    @Excel(name = "产品品类编号")
    private String productCategoryCode;

    /** 数量 */
    @Excel(name = "数量")
    private Long productNumber;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setProductionWarehousingCode(String productionWarehousingCode)
    {
        this.productionWarehousingCode = productionWarehousingCode;
    }

    public String getProductionWarehousingCode()
    {
        return productionWarehousingCode;
    }
    public void setProductCategoryCode(String productCategoryCode) 
    {
        this.productCategoryCode = productCategoryCode;
    }

    public String getProductCategoryCode() 
    {
        return productCategoryCode;
    }
    public void setProductNumber(Long productNumber) 
    {
        this.productNumber = productNumber;
    }

    public Long getProductNumber() 
    {
        return productNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("productionWarehousingCode", getProductionWarehousingCode())
            .append("productCategoryCode", getProductCategoryCode())
            .append("productNumber", getProductNumber())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

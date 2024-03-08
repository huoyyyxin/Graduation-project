package com.ruoyi.trace.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 销售出库对象 trace_sale_outbound
 * 
 * @author zhang
 * @date 2024-03-08
 */
public class SaleOutbound extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 销售出库单编号 */
    @Excel(name = "销售出库单编号")
    private String saleOutboundId;

    /** 销售单编号 */
    @Excel(name = "销售单编号")
    private String saleOrderId;

    /** 库房编号 */
    @Excel(name = "库房编号")
    private String warehouseId;

    /** 制单人编号 */
    @Excel(name = "制单人编号")
    private String tableCreatorId;

    /** 制单时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "制单时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date tableDate;

    /** 库管员编号 */
    @Excel(name = "库管员编号")
    private String warehouseKeeperId;

    /** 出库日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出库日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date saleOutboundDate;

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
    public void setSaleOrderId(String saleOrderId) 
    {
        this.saleOrderId = saleOrderId;
    }

    public String getSaleOrderId() 
    {
        return saleOrderId;
    }
    public void setWarehouseId(String warehouseId) 
    {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseId() 
    {
        return warehouseId;
    }
    public void setTableCreatorId(String tableCreatorId) 
    {
        this.tableCreatorId = tableCreatorId;
    }

    public String getTableCreatorId() 
    {
        return tableCreatorId;
    }
    public void setTableDate(Date tableDate) 
    {
        this.tableDate = tableDate;
    }

    public Date getTableDate() 
    {
        return tableDate;
    }
    public void setWarehouseKeeperId(String warehouseKeeperId) 
    {
        this.warehouseKeeperId = warehouseKeeperId;
    }

    public String getWarehouseKeeperId() 
    {
        return warehouseKeeperId;
    }
    public void setSaleOutboundDate(Date saleOutboundDate) 
    {
        this.saleOutboundDate = saleOutboundDate;
    }

    public Date getSaleOutboundDate() 
    {
        return saleOutboundDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sale OutboundId", getSaleOutboundId())
            .append("saleOrderId", getSaleOrderId())
            .append("warehouseId", getWarehouseId())
            .append("tableCreatorId", getTableCreatorId())
            .append("tableDate", getTableDate())
            .append("warehouseKeeperId", getWarehouseKeeperId())
            .append("saleOutboundDate", getSaleOutboundDate())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

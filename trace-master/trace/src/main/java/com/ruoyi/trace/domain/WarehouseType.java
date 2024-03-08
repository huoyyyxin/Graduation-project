package com.ruoyi.trace.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * warehouse_type对象 trace_warehouse_type
 * 
 * @author zhang
 * @date 2024-01-26
 */
public class WarehouseType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long warehouseTypeId;

    /** 类型 */
    @Excel(name = "类型")
    private String warehouseTypeType;

    /** 备注 */
    @Excel(name = "备注")
    private String warehouseTypeNotes;

    public void setWarehouseTypeId(Long warehouseTypeId) 
    {
        this.warehouseTypeId = warehouseTypeId;
    }

    public Long getWarehouseTypeId() 
    {
        return warehouseTypeId;
    }
    public void setWarehouseTypeType(String warehouseTypeType) 
    {
        this.warehouseTypeType = warehouseTypeType;
    }

    public String getWarehouseTypeType() 
    {
        return warehouseTypeType;
    }
    public void setWarehouseTypeNotes(String warehouseTypeNotes) 
    {
        this.warehouseTypeNotes = warehouseTypeNotes;
    }

    public String getWarehouseTypeNotes() 
    {
        return warehouseTypeNotes;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("warehouseTypeId", getWarehouseTypeId())
            .append("warehouseTypeType", getWarehouseTypeType())
            .append("warehouseTypeNotes", getWarehouseTypeNotes())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

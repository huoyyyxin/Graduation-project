package com.ruoyi.trace.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * warehouse对象 trace_warehouse
 * 
 * @author zhang
 * @date 2024-01-25
 */
@Data
public class Warehouse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 仓库名字 */
    @Excel(name = "仓库名字")
    private String warehouseName;

    /** 1:常温库 2:阴凉库 3:冷库 */
    @Excel(name = "1:常温库 2:阴凉库 3:冷库")
    private Integer warehouseTypes;

    /** 备注 */
    @Excel(name = "备注")
    private String warehouseRemark;
}

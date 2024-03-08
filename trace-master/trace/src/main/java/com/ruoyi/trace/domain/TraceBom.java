package com.ruoyi.trace.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * BOM信息对象 trace_bom
 * 
 * @author ruoyi
 * @date 2024-02-21
 */
@Data
public class TraceBom extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 产品编号 */
    private int productId;

    /** 原材料编号 */
    private String materialId;

    /** 数量 */
    @Excel(name = "数量")
    private Long bomNumber;
}

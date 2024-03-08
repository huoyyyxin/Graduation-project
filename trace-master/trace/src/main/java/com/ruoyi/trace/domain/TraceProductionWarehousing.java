package com.ruoyi.trace.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 生产入库对象 trace_production_warehousing
 * 
 * @author ruoyi
 * @date 2024-02-26
 */
@Data
public class TraceProductionWarehousing extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 生产入库单编号 */
    @Excel(name = "生产入库单编号")
    private String productionWarehousingCode;

    /** 生产计划单编号 */
    @Excel(name = "生产计划单编号")
    private String productionPlanningCode;

    /** 生产批号 */
    @Excel(name = "生产批号")
    private String productionBatchCode;

    /** 灭菌批号 */
    @Excel(name = "灭菌批号")
    private String productionSterilizationCode;

    /** 入库库房编号 */
    @Excel(name = "入库库房编号")
    private String productionWarehouseCode;

    /** 入库时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入库时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date productionWarehousingDate;

    /** 库管员 */
    @Excel(name = "库管员")
    private String productionWarehouseKeeper;

    /** 制单日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "制单日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date productionListDate;

    /** 制单人 */
    @Excel(name = "制单人")
    private String productListCreator;

    private TraceProductWarehousingDetail traceProductWarehousingDetail;


}

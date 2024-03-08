package com.ruoyi.trace.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * parameter对象 trace_system_parameter
 * 
 * @author zhang
 * @date 2024-01-26
 */
public class SystemParameter extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long systemParameterId;

    /** 含义 */
    @Excel(name = "含义")
    private String systemParameterMeaning;

    /** 参数值 */
    @Excel(name = "参数值")
    private String systemParameterValue;

    /** 备注说明 */
    @Excel(name = "备注说明")
    private String systemParameterRemark;

    public void setSystemParameterId(Long systemParameterId) 
    {
        this.systemParameterId = systemParameterId;
    }

    public Long getSystemParameterId() 
    {
        return systemParameterId;
    }
    public void setSystemParameterMeaning(String systemParameterMeaning) 
    {
        this.systemParameterMeaning = systemParameterMeaning;
    }

    public String getSystemParameterMeaning() 
    {
        return systemParameterMeaning;
    }
    public void setSystemParameterValue(String systemParameterValue) 
    {
        this.systemParameterValue = systemParameterValue;
    }

    public String getSystemParameterValue() 
    {
        return systemParameterValue;
    }
    public void setSystemParameterRemark(String systemParameterRemark) 
    {
        this.systemParameterRemark = systemParameterRemark;
    }

    public String getSystemParameterRemark() 
    {
        return systemParameterRemark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("systemParameterId", getSystemParameterId())
            .append("systemParameterMeaning", getSystemParameterMeaning())
            .append("systemParameterValue", getSystemParameterValue())
            .append("systemParameterRemark", getSystemParameterRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

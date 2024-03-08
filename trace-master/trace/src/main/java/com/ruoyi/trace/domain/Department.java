package com.ruoyi.trace.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * department对象 trace_department
 * 
 * @author zhang
 * @date 2024-01-26
 */
public class Department extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 部门编号 */
    private Long departmentId;

    /** 部门名称 */
    @Excel(name = "部门名称")
    private String departmentName;

    /** 备注 */
    @Excel(name = "备注")
    private String departmentRemarks;

    public void setDepartmentId(Long departmentId) 
    {
        this.departmentId = departmentId;
    }

    public Long getDepartmentId() 
    {
        return departmentId;
    }
    public void setDepartmentName(String departmentName) 
    {
        this.departmentName = departmentName;
    }

    public String getDepartmentName() 
    {
        return departmentName;
    }
    public void setDepartmentRemarks(String departmentRemarks) 
    {
        this.departmentRemarks = departmentRemarks;
    }

    public String getDepartmentRemarks() 
    {
        return departmentRemarks;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("departmentId", getDepartmentId())
            .append("departmentName", getDepartmentName())
            .append("departmentRemarks", getDepartmentRemarks())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

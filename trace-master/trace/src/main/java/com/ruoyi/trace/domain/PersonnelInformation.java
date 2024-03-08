package com.ruoyi.trace.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * personnel_information对象 trace_personnel_information
 * 
 * @author zhang
 * @date 2024-01-27
 */
public class PersonnelInformation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @Excel(name = "编号")
    private String personnelInformationId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String personnelInformationName;

    /** 性别 */
    @Excel(name = "性别")
    private String personnelInformationGender;

    /** 职务 */
    @Excel(name = "职务")
    private String personnelInformationDuty;

    /** 部门编号 */
    @Excel(name = "部门编号")
    private Long personnelInformationDepartNumber;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String personnelInformationPhone;

    /** 登录密码 */
    @Excel(name = "登录密码")
    private String personnelInformationPassword;

    /** 是否登录系统 0不可登录 1可登录 */
    @Excel(name = "是否登录系统 0不可登录 1可登录")
    private Integer personnelInformationLog;

    /** 备注 */
    @Excel(name = "备注")
    private String personnelInformationRemark;

    public void setPersonnelInformationId(String personnelInformationId) 
    {
        this.personnelInformationId = personnelInformationId;
    }

    public String getPersonnelInformationId() 
    {
        return personnelInformationId;
    }
    public void setPersonnelInformationName(String personnelInformationName) 
    {
        this.personnelInformationName = personnelInformationName;
    }

    public String getPersonnelInformationName() 
    {
        return personnelInformationName;
    }
    public void setPersonnelInformationGender(String personnelInformationGender) 
    {
        this.personnelInformationGender = personnelInformationGender;
    }

    public String getPersonnelInformationGender() 
    {
        return personnelInformationGender;
    }
    public void setPersonnelInformationDuty(String personnelInformationDuty) 
    {
        this.personnelInformationDuty = personnelInformationDuty;
    }

    public String getPersonnelInformationDuty() 
    {
        return personnelInformationDuty;
    }
    public void setPersonnelInformationDepartNumber(Long personnelInformationDepartNumber) 
    {
        this.personnelInformationDepartNumber = personnelInformationDepartNumber;
    }

    public Long getPersonnelInformationDepartNumber() 
    {
        return personnelInformationDepartNumber;
    }
    public void setPersonnelInformationPhone(String personnelInformationPhone) 
    {
        this.personnelInformationPhone = personnelInformationPhone;
    }

    public String getPersonnelInformationPhone() 
    {
        return personnelInformationPhone;
    }
    public void setPersonnelInformationPassword(String personnelInformationPassword) 
    {
        this.personnelInformationPassword = personnelInformationPassword;
    }

    public String getPersonnelInformationPassword() 
    {
        return personnelInformationPassword;
    }
    public void setPersonnelInformationLog(Integer personnelInformationLog) 
    {
        this.personnelInformationLog = personnelInformationLog;
    }

    public Integer getPersonnelInformationLog() 
    {
        return personnelInformationLog;
    }
    public void setPersonnelInformationRemark(String personnelInformationRemark) 
    {
        this.personnelInformationRemark = personnelInformationRemark;
    }

    public String getPersonnelInformationRemark() 
    {
        return personnelInformationRemark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("personnelInformationId", getPersonnelInformationId())
            .append("personnelInformationName", getPersonnelInformationName())
            .append("personnelInformationGender", getPersonnelInformationGender())
            .append("personnelInformationDuty", getPersonnelInformationDuty())
            .append("personnelInformationDepartNumber", getPersonnelInformationDepartNumber())
            .append("personnelInformationPhone", getPersonnelInformationPhone())
            .append("personnelInformationPassword", getPersonnelInformationPassword())
            .append("personnelInformationLog", getPersonnelInformationLog())
            .append("personnelInformationRemark", getPersonnelInformationRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

package com.ruoyi.trace.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
/**
 * personnel _permission对象 trace_personnel _permission
 *
 * @author zhang
 * @date 2024-01-27
 */
public class PersonnelPermission extends BaseEntity{
    private static final long serialVersionUID = 1L;

    private Long jobId;

    @Excel(name = "员工姓名")
    private String jobName;

    @Excel(name = "所拥有权限")
    private String authority;

    @Excel(name = "备注")
    private String PersonnelPermissionRemarks;

    public void setjobId(Long jobId)
    {
        this.jobId = jobId;
    }

    public Long getJobId() {
        return jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getPersonnelPermissionRemarks() {
        return PersonnelPermissionRemarks;
    }

    public void setPersonnelPermissionRemarks(String personnelPermissionRemarks) {
        PersonnelPermissionRemarks = personnelPermissionRemarks;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("jobId", getJobName())
                .append("jobName", getJobName())
                .append("PersonnelPermissionRemarks", getPersonnelPermissionRemarks())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}

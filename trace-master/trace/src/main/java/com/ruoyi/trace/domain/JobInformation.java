package com.ruoyi.trace.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * job对象 trace_job_information
 * 
 * @author zhang
 * @date 2024-01-26
 */
public class JobInformation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 岗位编号 */
    private Long jobId;

    /** 名称 */
    @Excel(name = "名称")
    private String jobName;

    /** 备注 */
    @Excel(name = "备注")
    private String jobRemarks;

    public void setJobId(Long jobId) 
    {
        this.jobId = jobId;
    }

    public Long getJobId() 
    {
        return jobId;
    }
    public void setJobName(String jobName) 
    {
        this.jobName = jobName;
    }

    public String getJobName() 
    {
        return jobName;
    }
    public void setJobRemarks(String jobRemarks) 
    {
        this.jobRemarks = jobRemarks;
    }

    public String getJobRemarks() 
    {
        return jobRemarks;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("jobId", getJobId())
            .append("jobName", getJobName())
            .append("jobRemarks", getJobRemarks())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

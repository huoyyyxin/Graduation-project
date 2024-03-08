package com.ruoyi.trace.mapper;

import java.util.List;
import com.ruoyi.trace.domain.JobInformation;

/**
 * jobMapper接口
 * 
 * @author zhang
 * @date 2024-01-26
 */
public interface JobInformationMapper 
{
    /**
     * 查询job
     * 
     * @param jobId job主键
     * @return job
     */
    public JobInformation selectJobInformationByJobId(Long jobId);

    /**
     * 查询job列表
     * 
     * @param jobInformation job
     * @return job集合
     */
    public List<JobInformation> selectJobInformationList(JobInformation jobInformation);

    /**
     * 新增job
     * 
     * @param jobInformation job
     * @return 结果
     */
    public int insertJobInformation(JobInformation jobInformation);

    /**
     * 修改job
     * 
     * @param jobInformation job
     * @return 结果
     */
    public int updateJobInformation(JobInformation jobInformation);

    /**
     * 删除job
     * 
     * @param jobId job主键
     * @return 结果
     */
    public int deleteJobInformationByJobId(Long jobId);

    /**
     * 批量删除job
     * 
     * @param jobIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteJobInformationByJobIds(Long[] jobIds);
}

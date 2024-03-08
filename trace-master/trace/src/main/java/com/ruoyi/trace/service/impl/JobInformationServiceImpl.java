package com.ruoyi.trace.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.trace.mapper.JobInformationMapper;
import com.ruoyi.trace.domain.JobInformation;
import com.ruoyi.trace.service.IJobInformationService;

/**
 * jobService业务层处理
 * 
 * @author zhang
 * @date 2024-01-26
 */
@Service
public class JobInformationServiceImpl implements IJobInformationService 
{
    @Autowired
    private JobInformationMapper jobInformationMapper;

    /**
     * 查询job
     * 
     * @param jobId job主键
     * @return job
     */
    @Override
    public JobInformation selectJobInformationByJobId(Long jobId)
    {
        return jobInformationMapper.selectJobInformationByJobId(jobId);
    }

    /**
     * 查询job列表
     * 
     * @param jobInformation job
     * @return job
     */
    @Override
    public List<JobInformation> selectJobInformationList(JobInformation jobInformation)
    {
        return jobInformationMapper.selectJobInformationList(jobInformation);
    }

    /**
     * 新增job
     * 
     * @param jobInformation job
     * @return 结果
     */
    @Override
    public int insertJobInformation(JobInformation jobInformation)
    {
        jobInformation.setCreateTime(DateUtils.getNowDate());
        return jobInformationMapper.insertJobInformation(jobInformation);
    }

    /**
     * 修改job
     * 
     * @param jobInformation job
     * @return 结果
     */
    @Override
    public int updateJobInformation(JobInformation jobInformation)
    {
        jobInformation.setUpdateTime(DateUtils.getNowDate());
        return jobInformationMapper.updateJobInformation(jobInformation);
    }

    /**
     * 批量删除job
     * 
     * @param jobIds 需要删除的job主键
     * @return 结果
     */
    @Override
    public int deleteJobInformationByJobIds(Long[] jobIds)
    {
        return jobInformationMapper.deleteJobInformationByJobIds(jobIds);
    }

    /**
     * 删除job信息
     * 
     * @param jobId job主键
     * @return 结果
     */
    @Override
    public int deleteJobInformationByJobId(Long jobId)
    {
        return jobInformationMapper.deleteJobInformationByJobId(jobId);
    }
}

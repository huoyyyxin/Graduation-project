package com.ruoyi.trace.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.trace.mapper.SystemParameterMapper;
import com.ruoyi.trace.domain.SystemParameter;
import com.ruoyi.trace.service.ISystemParameterService;

/**
 * parameterService业务层处理
 * 
 * @author zhang
 * @date 2024-01-26
 */
@Service
public class SystemParameterServiceImpl implements ISystemParameterService 
{
    @Autowired
    private SystemParameterMapper systemParameterMapper;

    /**
     * 查询parameter
     * 
     * @param systemParameterId parameter主键
     * @return parameter
     */
    @Override
    public SystemParameter selectSystemParameterBySystemParameterId(Long systemParameterId)
    {
        return systemParameterMapper.selectSystemParameterBySystemParameterId(systemParameterId);
    }

    /**
     * 查询parameter列表
     * 
     * @param systemParameter parameter
     * @return parameter
     */
    @Override
    public List<SystemParameter> selectSystemParameterList(SystemParameter systemParameter)
    {
        return systemParameterMapper.selectSystemParameterList(systemParameter);
    }

    /**
     * 新增parameter
     * 
     * @param systemParameter parameter
     * @return 结果
     */
    @Override
    public int insertSystemParameter(SystemParameter systemParameter)
    {
        systemParameter.setCreateTime(DateUtils.getNowDate());
        return systemParameterMapper.insertSystemParameter(systemParameter);
    }

    /**
     * 修改parameter
     * 
     * @param systemParameter parameter
     * @return 结果
     */
    @Override
    public int updateSystemParameter(SystemParameter systemParameter)
    {
        systemParameter.setUpdateTime(DateUtils.getNowDate());
        return systemParameterMapper.updateSystemParameter(systemParameter);
    }

    /**
     * 批量删除parameter
     * 
     * @param systemParameterIds 需要删除的parameter主键
     * @return 结果
     */
    @Override
    public int deleteSystemParameterBySystemParameterIds(Long[] systemParameterIds)
    {
        return systemParameterMapper.deleteSystemParameterBySystemParameterIds(systemParameterIds);
    }

    /**
     * 删除parameter信息
     * 
     * @param systemParameterId parameter主键
     * @return 结果
     */
    @Override
    public int deleteSystemParameterBySystemParameterId(Long systemParameterId)
    {
        return systemParameterMapper.deleteSystemParameterBySystemParameterId(systemParameterId);
    }
}

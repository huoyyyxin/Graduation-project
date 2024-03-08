package com.ruoyi.trace.mapper;

import java.util.List;
import com.ruoyi.trace.domain.SystemParameter;

/**
 * parameterMapper接口
 * 
 * @author zhang
 * @date 2024-01-26
 */
public interface SystemParameterMapper 
{
    /**
     * 查询parameter
     * 
     * @param systemParameterId parameter主键
     * @return parameter
     */
    public SystemParameter selectSystemParameterBySystemParameterId(Long systemParameterId);

    /**
     * 查询parameter列表
     * 
     * @param systemParameter parameter
     * @return parameter集合
     */
    public List<SystemParameter> selectSystemParameterList(SystemParameter systemParameter);

    /**
     * 新增parameter
     * 
     * @param systemParameter parameter
     * @return 结果
     */
    public int insertSystemParameter(SystemParameter systemParameter);

    /**
     * 修改parameter
     * 
     * @param systemParameter parameter
     * @return 结果
     */
    public int updateSystemParameter(SystemParameter systemParameter);

    /**
     * 删除parameter
     * 
     * @param systemParameterId parameter主键
     * @return 结果
     */
    public int deleteSystemParameterBySystemParameterId(Long systemParameterId);

    /**
     * 批量删除parameter
     * 
     * @param systemParameterIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSystemParameterBySystemParameterIds(Long[] systemParameterIds);
}

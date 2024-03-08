package com.ruoyi.trace.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.trace.mapper.PersonnelInformationMapper;
import com.ruoyi.trace.domain.PersonnelInformation;
import com.ruoyi.trace.service.IPersonnelInformationService;

import javax.net.ssl.SSLServerSocket;

/**
 * personnel_informationService业务层处理
 * 
 * @author zhang
 * @date 2024-01-27
 */
@Service
public class PersonnelInformationServiceImpl implements IPersonnelInformationService 
{
    @Autowired
    private PersonnelInformationMapper personnelInformationMapper;

    /**
     * 查询personnel_information
     * 
     * @param personnelInformationId personnel_information主键
     * @return personnel_information
     */
    @Override
    public PersonnelInformation selectPersonnelInformationByPersonnelInformationId(String personnelInformationId)
    {
        return personnelInformationMapper.selectPersonnelInformationByPersonnelInformationId(personnelInformationId);
    }

    /**
     * 查询personnel_information列表
     * 
     * @param personnelInformation personnel_information
     * @return personnel_information
     */
    @Override
    public List<PersonnelInformation> selectPersonnelInformationList(PersonnelInformation personnelInformation)
    {
        return personnelInformationMapper.selectPersonnelInformationList(personnelInformation);
    }

    /**
     * 新增personnel_information
     * 
     * @param personnelInformation personnel_information
     * @return 结果
     */
    @Override
    public int insertPersonnelInformation(PersonnelInformation personnelInformation)
    {
        personnelInformation.setCreateTime(DateUtils.getNowDate());
        return personnelInformationMapper.insertPersonnelInformation(personnelInformation);
    }

    /**
     * 修改personnel_information
     * 
     * @param personnelInformation personnel_information
     * @return 结果
     */
    @Override
    public int updatePersonnelInformation(PersonnelInformation personnelInformation)
    {
        personnelInformation.setUpdateTime(DateUtils.getNowDate());
        return personnelInformationMapper.updatePersonnelInformation(personnelInformation);
    }

    /**
     * 批量删除personnel_information
     * 
     * @param personnelInformationIds 需要删除的personnel_information主键
     * @return 结果
     */
    @Override
    public int deletePersonnelInformationByPersonnelInformationIds(String[] personnelInformationIds)
    {
        return personnelInformationMapper.deletePersonnelInformationByPersonnelInformationIds(personnelInformationIds);
    }

    /**
     * 删除personnel_information信息
     * 
     * @param personnelInformationId personnel_information主键
     * @return 结果
     */
    @Override
    public int deletePersonnelInformationByPersonnelInformationId(String personnelInformationId)
    {
        return personnelInformationMapper.deletePersonnelInformationByPersonnelInformationId(personnelInformationId);
    }

}

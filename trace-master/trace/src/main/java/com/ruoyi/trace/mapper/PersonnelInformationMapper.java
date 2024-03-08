package com.ruoyi.trace.mapper;

import java.util.List;
import com.ruoyi.trace.domain.PersonnelInformation;

/**
 * personnel_informationMapper接口
 * 
 * @author zhang
 * @date 2024-01-27
 */
public interface PersonnelInformationMapper 
{
    /**
     * 查询personnel_information
     * 
     * @param personnelInformationId personnel_information主键
     * @return personnel_information
     */
    public PersonnelInformation selectPersonnelInformationByPersonnelInformationId(String personnelInformationId);

    /**
     * 查询personnel_information列表
     * 
     * @param personnelInformation personnel_information
     * @return personnel_information集合
     */
    public List<PersonnelInformation> selectPersonnelInformationList(PersonnelInformation personnelInformation);

    /**
     * 新增personnel_information
     * 
     * @param personnelInformation personnel_information
     * @return 结果
     */
    public int insertPersonnelInformation(PersonnelInformation personnelInformation);

    /**
     * 修改personnel_information
     * 
     * @param personnelInformation personnel_information
     * @return 结果
     */
    public int updatePersonnelInformation(PersonnelInformation personnelInformation);

    /**
     * 删除personnel_information
     * 
     * @param personnelInformationId personnel_information主键
     * @return 结果
     */
    public int deletePersonnelInformationByPersonnelInformationId(String personnelInformationId);

    /**
     * 批量删除personnel_information
     * 
     * @param personnelInformationIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePersonnelInformationByPersonnelInformationIds(String[] personnelInformationIds);
}
